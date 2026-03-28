package ankit.ai_health_assistant.Repository;

import ankit.ai_health_assistant.Model.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatSessionRepository extends JpaRepository<ChatSession, Long> {

    List<ChatSession> findByUserIdAndIsDeletedFalse(Long userId);
}