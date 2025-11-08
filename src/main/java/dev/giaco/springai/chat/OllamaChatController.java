package dev.giaco.springai.chat;

import java.util.Map;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class OllamaChatController {
    
    private final OllamaChatModel chatModel;
    
    public OllamaChatController(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/cats")
    public Map<String, String> catsBreeds() {
        return Map.of("generation", chatModel.call("Give me 10 breeds of cats"));
    }

    @GetMapping("/java")
    public Flux<String> javaCuriosities() {
        return chatModel.stream(new String("How I can declare a variable in Java?"));
    }
    
}
