package ankit.ai_health_assistant.Model;

import ankit.ai_health_assistant.Enum.Language;
import ankit.ai_health_assistant.Enum.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "org_users")
public class OrgUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(unique = true, nullable = false)
    private String emailAddress ;

    @Column(unique = true)
    private String userName ;

    @Column(nullable = false)
    private String password ;

    @Enumerated(EnumType.STRING)
    private UserRole role ;

    @Enumerated(EnumType.STRING)
    private Language userLanguage ;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
