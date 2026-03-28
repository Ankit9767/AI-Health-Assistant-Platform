package ankit.ai_health_assistant.Repository;

import ankit.ai_health_assistant.Model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    List<Reminder> findByUserIdAndIsCompletedFalse(Long userId);
}