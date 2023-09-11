package academy.prog.chatserversprint.controler;

import academy.prog.chatserversprint.model.Message;
import academy.prog.chatserversprint.repo.MessageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/active-users")
public class ActiveUserController {
    private final MessageRepository messageRepository;

    public ActiveUserController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public List<Message> getActiveUsers() {
        return messageRepository.findActiveUsers();
    }
}
