package ankit.ai_health_assistant.Service;

import ankit.ai_health_assistant.Model.ChatSession;
import ankit.ai_health_assistant.Model.OrgUser;
import ankit.ai_health_assistant.Repository.ChatSessionRepository;
import ankit.ai_health_assistant.Repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatSessionService {

    @Autowired
    private final ChatSessionRepository chatSessionRepository ;

    @Autowired
    private final MessageRepository messageRepository ;

    @Transactional
    public void deleteSession(Long sessionId, OrgUser user) {

        ChatSession session = chatSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        if (!(session.getUser().getId() == (user.getId()))) {
            throw new RuntimeException("Unauthorized");
        }
        messageRepository.deleteBySessionId(sessionId);
        chatSessionRepository.delete(session);
    }

    public void renameSession(Long sessionId, String title, OrgUser user) {

        ChatSession session = chatSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        if (!(session.getUser().getId() == (user.getId()))) {
            throw new RuntimeException("Unauthorized");
        }

        session.setTitle(title);
        chatSessionRepository.save(session);
    }
}
