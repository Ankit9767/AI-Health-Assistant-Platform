package ankit.ai_health_assistant.Dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponseDto {
    private Long userId;
    private String emailAddress;
    private String message;
    private String token;
}