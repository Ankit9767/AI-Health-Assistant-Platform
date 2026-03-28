package ankit.ai_health_assistant.Repository;

import ankit.ai_health_assistant.Model.HealthMetric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthMetricRepository extends JpaRepository<HealthMetric, Long> {

    List<HealthMetric> findByUserIdOrderByRecordedAtDesc(Long userId);
}