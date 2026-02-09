package dev.giaco.springai.multimodality.audio;

import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechModel;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class AudioGeneration {

    // private final OpenAiAudioSpeechModel audioSpeechModel;
    private final SpeechModel speechModel;

    public AudioGeneration(SpeechModel speechModel) {
        this.speechModel = speechModel;
    }

    @GetMapping("/speak")
    public ResponseEntity<byte[]> generateSpeech(
            @RequestParam(defaultValue = "Hello, world!") String text) {

        var options = OpenAiAudioSpeechOptions.builder()
                .model("tts-1-hd")
                .voice(OpenAiAudioApi.SpeechRequest.Voice.ALLOY) // voices: ALLOY, COPPER, IRON, STEEL
                .speed(1.0f)
                .build();

        SpeechPrompt speechPrompt = new SpeechPrompt(text, options);
        SpeechResponse speechResponse = speechModel.call(speechPrompt);

        byte[] audioData = speechResponse.getResult().getOutput();

        return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_TYPE, "audio/mpeg")
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\speech.mp3\"")
        .body(audioData);
    }

}
