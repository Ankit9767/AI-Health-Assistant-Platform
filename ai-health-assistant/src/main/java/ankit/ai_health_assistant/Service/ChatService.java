package ankit.ai_health_assistant.Service;

import ankit.ai_health_assistant.Dto.ChatRequestDto;
import ankit.ai_health_assistant.Dto.ChatSessionResponseDto;
import ankit.ai_health_assistant.Dto.MessageResponseDto;
import ankit.ai_health_assistant.Model.*;
import ankit.ai_health_assistant.Repository.*;
import ankit.ai_health_assistant.Enum.MessageRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatSessionRepository chatSessionRepository;
    private final MessageRepository messageRepository;
    private final AiChatService aiChatService;

    public String chat(ChatRequestDto request, OrgUser user) {

        ChatSession session;

        if (request.getSessionId() == null) {
            session = new ChatSession();
            session.setUser(user);

            String dynamicTitle = aiChatService.generateChatTitle(
                    request.getMessage(),
                    user.getUserLanguage().name());

            session.setTitle(dynamicTitle);
            session.setLanguage(user.getUserLanguage());
            session = chatSessionRepository.save(session);
        } else {
            session = chatSessionRepository.findById(request.getSessionId())
                    .orElseThrow(() -> new RuntimeException("Session not found"));
        }

        Message userMsg = new Message();
        userMsg.setContent(request.getMessage());
        userMsg.setRole(MessageRole.USER);
        userMsg.setSession(session);
        messageRepository.save(userMsg);

        String language = session.getLanguage() != null
                ? session.getLanguage().name()
                : "English";

        String aiResponse = aiChatService.chat(
                request.getMessage(),
                language,
                session.getId().toString()
        );

        Message aiMsg = new Message();
        aiMsg.setContent(aiResponse);
        aiMsg.setRole(MessageRole.AI);
        aiMsg.setSession(session);
        messageRepository.save(aiMsg);

        return aiResponse;
    }

    public String ask(ChatRequestDto request, OrgUser user) {

        ChatSession session;

        if (request.getSessionId() == null) {
            session = new ChatSession();
            session.setUser(user);

            String dynamicTitle = aiChatService.generateChatTitle(
                    request.getMessage(),
                    user.getUserLanguage().name());

            session.setTitle(dynamicTitle);
            session.setLanguage(user.getUserLanguage());
            session = chatSessionRepository.save(session);
        } else {
            session = chatSessionRepository.findById(request.getSessionId())
                    .orElseThrow(() -> new RuntimeException("Session not found"));
        }

        Message userMsg = new Message();
        userMsg.setContent(request.getMessage());
        userMsg.setRole(MessageRole.USER);
        userMsg.setSession(session);
        messageRepository.save(userMsg);

        String language = session.getLanguage() != null
                ? session.getLanguage().name()
                : "English";

        String aiResponse = aiChatService.askHealthQuestion(
                request.getMessage(),
                language,
                session.getId().toString()
        );

        Message aiMsg = new Message();
        aiMsg.setContent(aiResponse);
        aiMsg.setRole(MessageRole.AI);
        aiMsg.setSession(session);
        messageRepository.save(aiMsg);

        return aiResponse;
    }

    public List<MessageResponseDto> getChatHistory(Long sessionId, OrgUser user) {

        ChatSession session = chatSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        // 🔐 Security check
        if (!(session.getUser().getId() == (user.getId()))) {
            throw new RuntimeException("Unauthorized access");
        }

        List<Message> messages = messageRepository
                .findBySessionIdOrderByCreatedAtAsc(sessionId);

        return messages.stream()
                .map(msg -> MessageResponseDto.builder()
                        .id(msg.getId())
                        .content(msg.getContent())
                        .role(msg.getRole())
                        .createdAt(msg.getCreatedAt())
                        .build())
                .toList();
    }

    public List<ChatSessionResponseDto> getUserSessions(OrgUser user) {

        List<ChatSession> sessions = chatSessionRepository
                .findByUserIdOrderByCreatedAtDesc(user.getId());

        return sessions.stream()
                .map(session -> ChatSessionResponseDto.builder()
                        .id(session.getId())
                        .title(session.getTitle())
                        .createdAt(session.getCreatedAt())
                        .build())
                .toList();
    }
}