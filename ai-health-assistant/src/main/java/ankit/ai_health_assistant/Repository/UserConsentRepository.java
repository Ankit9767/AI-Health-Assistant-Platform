package ankit.ai_health_assistant.Repository;

import ankit.ai_health_assistant.Model.UserConsent;
import ankit.ai_health_assistant.Enum.ConsentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserConsentRepository extends JpaRepository<UserConsent, Long> {

    Optional<UserConsent> findByUserIdAndConsentType(Long userId, ConsentType consentType);
}