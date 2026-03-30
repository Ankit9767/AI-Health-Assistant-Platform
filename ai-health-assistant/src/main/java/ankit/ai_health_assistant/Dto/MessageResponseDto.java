package ankit.ai_health_assistant.Dto;

import ankit.ai_health_assistant.Enum.MessageRole;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MessageResponseDto {

    private Long id;
    private String content;
    private MessageRole role;
    private LocalDateTime createdAt;
}