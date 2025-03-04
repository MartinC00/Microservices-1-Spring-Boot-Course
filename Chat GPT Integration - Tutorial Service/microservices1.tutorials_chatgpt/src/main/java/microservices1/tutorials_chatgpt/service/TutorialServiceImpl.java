package microservices1.tutorials_chatgpt.service;

import lombok.RequiredArgsConstructor;
import microservices1.tutorials_chatgpt.model.Tutorial;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.DefaultChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TutorialServiceImpl implements TutorialService{
    private ChatClient chatClient;

    public TutorialServiceImpl(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    private final String prompt = """
			"Quiero que me muestres una lista con los {total} primeros sitios Web, en orden de importancia, que incluyan tutoriales sobre {tematica}. 
			El resultado me lo mostrarás en una lista JSON, donde cada objeto incluye los siguientes campos: "url"(direccion del sitio), 
			"descripcion"(breve descripción del sitio Web) y "valoracion"(imagina que eres un experto en la materia y dame tu valoración del sitio entre 1, que significa mal tutorial,  y 5, que sería un muy buen tutorial)
			""";

    @Override
    public List<Tutorial> getTutorialsByTopic(String topic, int amountOfVideos) {
        BeanOutputConverter<Tutorial[]> converter = new BeanOutputConverter<>(Tutorial[].class);
        PromptTemplate template = new PromptTemplate(prompt);
        template.add("tematica", topic);
        template.add("total", amountOfVideos);
        ChatResponse chatResponse = chatClient.prompt(template.create()).call().chatResponse();
        return Arrays.asList(converter.convert(chatResponse.getResult().getOutput().getText()));
    }
}
