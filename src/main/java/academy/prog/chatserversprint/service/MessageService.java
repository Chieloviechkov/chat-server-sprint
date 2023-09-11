package academy.prog.chatserversprint.service;

import academy.prog.chatserversprint.model.Message;
import academy.prog.chatserversprint.model.MessageDTO;
import academy.prog.chatserversprint.repo.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public void add(MessageDTO messageDTO) {
        var message = Message.fromDTO(messageDTO);

        if (messageDTO.getFileNames() != null && messageDTO.getFileDataList() != null
                && messageDTO.getFileNames().size() == messageDTO.getFileDataList().size()) {
            message.setFileDataList(messageDTO.getFileDataList());
            message.setFileNames(messageDTO.getFileNames());
        }

        message.setRecipients(messageDTO.getRecipients());
        message.setOnlineStatus(true);
        message.setLastActivity(new Date());

        messageRepository.save(message);
    }

    @Transactional(readOnly = true)
    public List<MessageDTO> get(long id) {
        var messages = messageRepository.findNew(id);
        var result = new ArrayList<MessageDTO>();

        messages.forEach(message -> result.add(message.toDTO()));
        return result;
    }
    @Transactional(readOnly = true)
    public Message getMessageById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

}