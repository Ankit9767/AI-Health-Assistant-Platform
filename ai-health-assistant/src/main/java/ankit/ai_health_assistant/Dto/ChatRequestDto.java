package ankit.ai_health_assistant.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRequestDto {

    private Long sessionId;
    private String message;
}