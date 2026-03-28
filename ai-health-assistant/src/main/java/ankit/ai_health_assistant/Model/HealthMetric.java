package ankit.ai_health_assistant.Model;

import ankit.ai_health_assistant.Enum.MetricType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "health_metrics")
public class HealthMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MetricType metricType;

    private Double value;

    private String unit;

    private LocalDateTime recordedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private OrgUser user;
}