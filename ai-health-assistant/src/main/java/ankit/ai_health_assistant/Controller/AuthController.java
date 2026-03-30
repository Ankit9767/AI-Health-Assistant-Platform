package ankit.ai_health_assistant.Controller;

import ankit.ai_health_assistant.Dto.AuthResponseDto;
import ankit.ai_health_assistant.Dto.LoginRequestDto;
import ankit.ai_health_assistant.Dto.RegisterRequestDto;
import ankit.ai_health_assistant.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponseDto register(@RequestBody RegisterRequestDto request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody LoginRequestDto request) {
        return authService.login(request);
    }
}