package ankit.ai_health_assistant.Service;

import ankit.ai_health_assistant.Dto.AuthResponseDto;
import ankit.ai_health_assistant.Dto.RegisterRequestDto;
import ankit.ai_health_assistant.Model.OrgUser;
import ankit.ai_health_assistant.Repository.UserRepository;
import ankit.ai_health_assistant.Enum.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static ankit.ai_health_assistant.Enum.Constants.EMAIL_ALREADY_REGISTERED;
import static ankit.ai_health_assistant.Enum.Constants.USER_REGISTERED_SUCCESSFULLY;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthResponseDto register(RegisterRequestDto request) {

        if (userRepository.existsByEmailAddress(request.getEmailAddress())) {
            throw new RuntimeException(EMAIL_ALREADY_REGISTERED);
        }

        OrgUser user = new OrgUser();
        user.setEmailAddress(request.getEmailAddress());
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.USER);

        OrgUser savedUser = userRepository.save(user);

        return AuthResponseDto.builder()
                .userId(savedUser.getId())
                .emailAddress(savedUser.getEmailAddress())
                .message(USER_REGISTERED_SUCCESSFULLY)
                .build();
    }
}