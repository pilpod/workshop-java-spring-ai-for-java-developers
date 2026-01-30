package dev.giaco.springai.output;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DisneyWorld {

    private final ChatClient chatClient;

    public DisneyWorld(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/disney/characters/unstructured")
    public String unstructured() {
        return chatClient.prompt()
                .user("List five popular Disney characters with their year of birth.")
                .call()
                .content();
    }

    @GetMapping("/disney/characters/structured")
    public DisneyCharacters structured() {
        return chatClient.prompt()
                .user("List five popular Disney characters with their year of birth.")
                .call()
                .entity(DisneyCharacters.class);
    }
    
    

}
