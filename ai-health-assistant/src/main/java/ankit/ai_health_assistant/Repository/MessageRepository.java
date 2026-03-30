package ankit.ai_health_assistant.Repository;

import ankit.ai_health_assistant.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findBySessionIdOrderByCreatedAtAsc(Long sessionId);

    @Modifying
    @Query("DELETE FROM Message m WHERE m.session.id = :sessionId")
    void deleteBySessionId(Long sessionId);

}