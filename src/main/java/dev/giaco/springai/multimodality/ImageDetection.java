package dev.giaco.springai.multimodality;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ImageDetection {

    private final ChatClient chatClient;

    @Value("classpath:images/kittens-unsplash-resized.jpg")
    private Resource imageResource;

    public ImageDetection(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/image-to-text")
    public String imageToText() {
        return chatClient.prompt()
        .user(u -> u
            .text("Describe the image with maximum 30 words.")
            .media(MimeTypeUtils.IMAGE_JPEG, imageResource))
        .call()
        .content();
    }
    



}
