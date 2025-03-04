package microservices1.tutorials_chatgpt.controller;

import lombok.RequiredArgsConstructor;
import microservices1.tutorials_chatgpt.model.Tutorial;
import microservices1.tutorials_chatgpt.service.TutorialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tutorial")
public class TutorialController {
    private final TutorialService tutorialService;

    @GetMapping
    public ResponseEntity<List<Tutorial>> getTutorialsByTopic(@RequestParam("topic") String topic,
                                                              @RequestParam("amountOfVideos") int amountOfVideos){
        return new ResponseEntity<>(tutorialService.getTutorialsByTopic(topic, amountOfVideos), HttpStatus.OK);
    }
}
