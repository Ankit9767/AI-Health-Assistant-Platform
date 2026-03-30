package ankit.ai_health_assistant.Service;

import ankit.ai_health_assistant.Dto.AuthResponseDto;
import ankit.ai_health_assistant.Dto.LoginRequestDto;
import ankit.ai_health_assistant.Dto.RegisterRequestDto;
import ankit.ai_health_assistant.Enum.Language;
import ankit.ai_health_assistant.Model.OrgUser;
import ankit.ai_health_assistant.Repository.UserRepository;
import ankit.ai_health_assistant.Enum.UserRole;
import ankit.ai_health_assistant.Security.JwtProvider.JwtTokenProvider;
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

    @Autowired
    private JwtTokenProvider jwtTokenProvider ;

    public AuthResponseDto register(RegisterRequestDto request) {

        if (userRepository.existsByEmailAddress(request.getEmailAddress())) {
            throw new RuntimeException(EMAIL_ALREADY_REGISTERED);
        }

        OrgUser user = new OrgUser();
        user.setEmailAddress(request.getEmailAddress());
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.USER);
        user.setUserLanguage(Language.ENGLISH);


        OrgUser savedUser = userRepository.save(user);

        return AuthResponseDto.builder()
                .userId(savedUser.getId())
                .emailAddress(savedUser.getEmailAddress())
                .message(USER_REGISTERED_SUCCESSFULLY)
                .build();
    }

    public AuthResponseDto login(LoginRequestDto request) {

        OrgUser user = userRepository.findByEmailAddress(request.getEmailAddress())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtTokenProvider.generateToken(user.getEmailAddress());

        return AuthResponseDto.builder()
                .userId(user.getId())
                .emailAddress(user.getEmailAddress())
                .token(token)
                .message("Login successful")
                .build();
    }
}