package ankit.ai_health_assistant.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.ai.chat.prompt.PromptTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AiChatService {

    private final ChatClient chatClient;

    public String askHealthQuestion(String userMessage, String language, String conversationId) {

        PromptTemplate template = new PromptTemplate("""
            You are a professional AI health assistant.

            Rules:
            - Provide possible medical diagnoses for common or non-serious conditions.
            - For serious symptoms or red flags, always recommend consulting a doctor immediately.
            - Respond in a structured format:

            Diagnosis: <possible diagnosis>
            Explanation: <reasoning step by step>
            Guidance: <general guidance or next steps>
            Disclaimer: <always include disclaimers that this is AI-generated information>

            Respond in {language}

            User: {message}
            """);

        String prompt = template.create(Map.of(
                "language", language,
                "message", userMessage
        )).getContents();

        return chatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param("conversationId", conversationId))
                .call()
                .content();
    }

    public String chat(String userMessage, String language, String conversationId) {

        PromptTemplate template = new PromptTemplate("""
                You are a helpful health assistant.

                Respond in {language}

                User: {message}
                """);

        String prompt = template.create(Map.of(
                "language", language,
                "message", userMessage
        )).getContents();

        return chatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param("conversationId", conversationId))
                .call()
                .content();
    }

    public String generateChatTitle(String userMessage, String language) {

        PromptTemplate template = new PromptTemplate("""
        You are an AI assistant. 
        Based on the following user message, generate a short, catchy, 3–5 word title 
        that summarizes the topic of this chat. Respond only with the title, no extra text.

        Respond in {language}

        User: {message}
    """);

        String prompt = template.create(Map.of(
                "language", language,
                "message", userMessage
        )).getContents();

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content()
                .trim();
    }
}