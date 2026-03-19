package dev.giaco.springai.byod;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelComparison {

    private final ChatClient chatClient;

    public ModelComparison(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/models")
    public String compareModels() {
        return chatClient.prompt()
                .user("Can you give me an up to date list popular language models and their current context window?")
                .call()
                .content();
    }

    @GetMapping("/models/stuff-the-prompt")
    public String compareModelsStuffThePrompt() {

        var system = """
                If you're asked up to date language models and their context window here is some information to help you with your response:
                [
                {
                    "model": "OpenAI GPT-4.1 / GPT-4.1-Preview",
                    "max_context_window": "128k–200k tokens",
                    "company": "OpenAI"
                },
                {
                    "model": "OpenAI o3 / o3-mini",
                    "max_context_window": "200k tokens",
                    "company": "OpenAI"
                },
                {
                    "model": "Anthropic Claude 3.5 Sonnet",
                    "max_context_window": "200k tokens",
                    "company": "Anthropic"
                },
                {
                    "model": "Anthropic Claude 3.5 Opus",
                    "max_context_window": "200k tokens",
                    "company": "Anthropic"
                },
                {
                    "model": "Anthropic Claude 3 Haiku",
                    "max_context_window": "200k tokens",
                    "company": "Anthropic"
                },
                {
                    "model": "Google Gemini 2.0 Flash",
                    "max_context_window": "1M tokens",
                    "company": "Google"
                },
                {
                    "model": "Google Gemini 2.0 Pro",
                    "max_context_window": "2M tokens",
                    "company": "Google"
                },
                {
                    "model": "Google Gemini 2.0 Ultra",
                    "max_context_window": "2M tokens",
                    "company": "Google"
                },
                {
                    "model": "Meta Llama 3.1 (70B / 405B)",
                    "max_context_window": "128k tokens",
                    "company": "Meta"
                },
                {
                    "model": "Mistral Large 2",
                    "max_context_window": "128k tokens",
                    "company": "Mistral AI"
                },
                {
                    "model": "Mistral Small / Nemo",
                    "max_context_window": "32k–128k tokens",
                    "company": "Mistral AI"
                },
                {
                    "model": "Qwen2.5",
                    "max_context_window": "128k–1M tokens",
                    "company": "Alibaba"
                },
                {
                    "model": "DeepSeek R1",
                    "max_context_window": "1M tokens",
                    "company": "DeepSeek"
                }
                ]
                    """;

        return chatClient.prompt()
                .user("Can you give me an up to date list popular language models and their current context window?")
                .system(system)
                .call()
                .content();
    }

}
