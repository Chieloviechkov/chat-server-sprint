package academy.prog.chatserversprint.service;

import academy.prog.chatserversprint.model.Message;
import academy.prog.chatserversprint.repo.MessageRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private final MessageRepository messageRepository;

    public UserService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void checkUserActivity() {
        Date currentTime = new Date();
        List<Message> activeUsers = messageRepository.findActiveUsers();

        for (Message user : activeUsers) {
            Date lastActivity = user.getLastActivity();

            // Если пользователь неактивен в течение 5 минут, установите статус "оффлайн"
            if (lastActivity != null && currentTime.getTime() - lastActivity.getTime() > 5 * 60 * 1000) {
                user.setOnlineStatus(false);
                messageRepository.save(user);
            }
        }
    }
}
