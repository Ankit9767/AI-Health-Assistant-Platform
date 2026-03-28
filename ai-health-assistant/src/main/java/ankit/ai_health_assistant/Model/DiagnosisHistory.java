package ankit.ai_health_assistant.Model;

import ankit.ai_health_assistant.Enum.Severity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "diagnosis_history")
public class DiagnosisHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String symptoms;

    @Column(columnDefinition = "TEXT")
    private String aiSuggestion;

    @Enumerated(EnumType.STRING)
    private Severity severity;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private OrgUser user;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}