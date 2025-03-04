package microservices1.tutorials_chatgpt.service;

import microservices1.tutorials_chatgpt.model.Tutorial;

import java.util.List;

public interface TutorialService {
    List<Tutorial> getTutorialsByTopic(String topic, int amountOfVideos);
}
