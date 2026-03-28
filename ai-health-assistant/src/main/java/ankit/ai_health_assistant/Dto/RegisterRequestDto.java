package ankit.ai_health_assistant.Dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {
    private String emailAddress;
    private String userName;
    private String password;
}