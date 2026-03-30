package ankit.ai_health_assistant.Dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ChatSessionResponseDto {

    private Long id;
    private String title;
    private LocalDateTime createdAt;
}