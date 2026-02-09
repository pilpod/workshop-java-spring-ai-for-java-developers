package dev.giaco.springai.multimodality.image;

import java.util.Map;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.OpenAiImageOptions.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
* This class is responsible for handling image generation requests.
* It uses the ImageModel interface to generate images based on the provided input. 
* The OpenAiImageModel is an implementation of the ImageModel interface that interacts with the OpenAI API to generate images.
* https://docs.spring.io/spring-ai/reference/api/multimodality.html
*/

@RestController
public class ImageGeneration {

    // private final OpenAiImageModel openAiImageModel;
    private final ImageModel imageModel;

    public ImageGeneration(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @GetMapping("/generate-image")
    public ResponseEntity<Map<String, String>> generateImage(
            @RequestParam(defaultValue = "A beautiful sun set over the mountains") String prompt) {

        ImageOptions imageOptions = OpenAiImageOptions.builder()
                .model("dall-e-3")
                .width(1024)
                .height(1024)
                .quality("hd")
                .style("vivid")
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(prompt, imageOptions);
        ImageResponse imageResponse = imageModel.call(imagePrompt);

        String url = imageResponse.getResult().getOutput().getUrl();

        return ResponseEntity.ok(Map.of(
            "prompt", prompt,
            "url", url));
    }

}
