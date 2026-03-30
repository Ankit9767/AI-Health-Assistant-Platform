package ankit.ai_health_assistant.Controller;

import ankit.ai_health_assistant.Dto.ChatRequestDto;
import ankit.ai_health_assistant.Dto.MessageResponseDto;
import ankit.ai_health_assistant.Model.OrgUser;
import ankit.ai_health_assistant.Service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/ask")
    public String ask(@RequestBody ChatRequestDto request) {

        OrgUser user = (OrgUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return chatService.ask(request, user);
    }

    @PostMapping
    public String chat(@RequestBody ChatRequestDto request) {

        OrgUser user = (OrgUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return chatService.chat(request, user);
    }

    @GetMapping("/history/{sessionId}")
    public List<MessageResponseDto> getChatHistory(@PathVariable Long sessionId) {

        OrgUser user = (OrgUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return chatService.getChatHistory(sessionId, user);
    }
}