package ankit.ai_health_assistant.Repository;

import ankit.ai_health_assistant.Model.OrgUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<OrgUser, Long> {

    Optional<OrgUser> findByEmail(String email);

    Optional<OrgUser> findByUserName(String userName);

    boolean existsByEmail(String email);
}