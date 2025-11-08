package dev.giaco.springai.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.GetMapping;
@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    public String chat() {
        return chatClient.prompt()
                .user("Tell me a joke.")
                .call() // No devolverá el contenido hasta que toda la respuesta este lista. Bloquea la
                        // respuesta.
                .content();
    }

    @GetMapping("/stream")
    public Flux<String> stream() {
        return chatClient.prompt()
                .user("Tell me why the sky is blue.")
                .stream() // Utiliza WebFlux para enviar la respuesta a medida que se genera, sin esperar
                          // a que esté completa.
                .content();
    }

    @GetMapping("/cars")
    public ChatResponse carsContext() {
        return chatClient.prompt()
                .user("Give me 10 cars brand.")
                .call()
                .chatResponse();
    }

}
