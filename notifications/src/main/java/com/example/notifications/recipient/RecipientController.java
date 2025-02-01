package com.example.notifications.recipient;

import com.example.notifications.recipient.Recipient;
import com.example.notifications.recipient.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/recipient")
public class RecipientController {
    private final RecipientService recipientService;

    @Autowired
    public RecipientController(RecipientService recipientService) {
        this.recipientService = recipientService;
    }

    // All recipients
    @GetMapping
    public List<RecipientDTO> getAllRecipients() {
        return recipientService.getAllRecipients();
    }

    // Find recipient by id
    @GetMapping("/{recipientId}")
    public RecipientDTO getRecipientById(@PathVariable Long recipientId) {
        return recipientService.findRecipientById(recipientId);
    }

    // Create a new recipient
    @PostMapping("/")
    public RecipientDTO createRecipient(@RequestBody Recipient recipient) {
        return recipientService.addNewRecipient(recipient);
    }

    // Delete a recipient by id
    @DeleteMapping("/{id}")
    public void deleteRecipient(@PathVariable Long id) {
        recipientService.deleteRecipientById(id);
    }
}
