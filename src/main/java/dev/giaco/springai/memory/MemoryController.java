package dev.giaco.springai.memory;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MemoryController {

    private final ChatClient chatClient;

    public MemoryController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/memory")
    public String memory(@RequestParam String message) {
        
        return chatClient.prompt()
        .user(message)
        .call()
        .content();
    }
    
}
