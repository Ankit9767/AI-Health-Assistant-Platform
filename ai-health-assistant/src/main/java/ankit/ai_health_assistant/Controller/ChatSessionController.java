package ankit.ai_health_assistant.Controller;

import ankit.ai_health_assistant.Dto.ChatSessionResponseDto;
import ankit.ai_health_assistant.Dto.RenameSessionDto;
import ankit.ai_health_assistant.Model.OrgUser;
import ankit.ai_health_assistant.Service.ChatService;
import ankit.ai_health_assistant.Service.ChatSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class ChatSessionController {

    @Autowired
    private final ChatService chatService;

    @Autowired
    private final ChatSessionService chatSessionService ;

    @GetMapping
    public List<ChatSessionResponseDto> getSessions() {

        OrgUser user = (OrgUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return chatService.getUserSessions(user);
    }

    @DeleteMapping("/session/{sessionId}")
    public ResponseEntity<String> deleteSession(@PathVariable Long sessionId) {

        OrgUser user = (OrgUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        chatSessionService.deleteSession(sessionId, user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Session deleted successfully");
    }

    @PutMapping("/session/{sessionId}")
    public ResponseEntity<String> renameSession(@PathVariable Long sessionId,
                                                @RequestBody RenameSessionDto dto) {

        OrgUser user = (OrgUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        chatSessionService.renameSession(sessionId, dto.getTitle(), user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Session renamed successfully");
    }
}
