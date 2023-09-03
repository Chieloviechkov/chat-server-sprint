package academy.prog.chatserversprint.controler;

import academy.prog.chatserversprint.model.Message;
import academy.prog.chatserversprint.model.MessageDTO;
import academy.prog.chatserversprint.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("add")
    public ResponseEntity<Void> add(@RequestBody MessageDTO messageDTO) {
        if (messageDTO.getRecipients() == null || messageDTO.getRecipients().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        messageService.add(messageDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("get")
    public List<MessageDTO> get(
            @RequestParam(required = false, defaultValue = "0") Long from)
    {
        return messageService.get(from);
    }

    @GetMapping("test")
    public String test() {
        return "It works!";
    }

    @GetMapping("file")
    public ResponseEntity<MessageDTO> file(@RequestParam Long messageId) {
        Message message = messageService.getMessageById(messageId);

        if (message != null && message.getFileData() != null) {
            MessageDTO fileDTO = new MessageDTO();
            fileDTO.setFileName(message.getFileName());
            fileDTO.setFileData(message.getFileData());
            return new ResponseEntity<>(fileDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
