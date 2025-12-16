package dev.giaco.springai.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/nba")
public class NBASportController {

    private final ChatClient chatClient;

    public NBASportController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public String ask(@RequestParam("prompt") String param) {

        var systemInstruction = """
                You are a specialist about NBA.
                You can ONLY discuss:
                - Basketball sport
                - NBA Basketball

                If asked about anything else, respond: "I can't answer that. I only respond about basketball."
                """;

        return chatClient.prompt()
                .user(param)
                .system(systemInstruction)
                .call()
                .content();
    }

}
