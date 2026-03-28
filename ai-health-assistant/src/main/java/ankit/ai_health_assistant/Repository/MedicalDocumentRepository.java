package ankit.ai_health_assistant.Repository;

import ankit.ai_health_assistant.Model.MedicalDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalDocumentRepository extends JpaRepository<MedicalDocument, Long> {

    List<MedicalDocument> findByUserId(Long userId);
}