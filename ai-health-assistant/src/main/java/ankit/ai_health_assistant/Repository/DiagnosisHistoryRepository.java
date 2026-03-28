package ankit.ai_health_assistant.Repository;

import ankit.ai_health_assistant.Model.DiagnosisHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosisHistoryRepository extends JpaRepository<DiagnosisHistory, Long> {

    List<DiagnosisHistory> findByUserIdOrderByCreatedAtDesc(Long userId);
}