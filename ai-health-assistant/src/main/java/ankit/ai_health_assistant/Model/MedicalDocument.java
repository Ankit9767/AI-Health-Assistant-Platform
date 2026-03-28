package ankit.ai_health_assistant.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "medical_documents")
public class MedicalDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Column(columnDefinition = "TEXT")
    private String fileUrl;

    private String documentType;

    @Column(columnDefinition = "LONGTEXT")
    private String extractedText;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column(columnDefinition = "JSON")
    private String insights;

    @Column(columnDefinition = "JSON")
    private String abnormalFlags;

    @Column(name = "uploaded_at", nullable = false)
    private LocalDateTime uploadedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private OrgUser user;

    @PrePersist
    protected void onUpload() {
        this.uploadedAt = LocalDateTime.now();
    }
}