package ankit.ai_health_assistant.Model;

import ankit.ai_health_assistant.Enum.MessageRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MessageRole role;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String messageType = "TEXT";

    @Column(columnDefinition = "JSON")
    private String metadata;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private ChatSession session;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}