package com.example.notifications.recipient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipientService {
    private final RecipientRepoService recipientRepoService;
    private final RecipientMapper recipientMapper;

    @Autowired
    public RecipientService(RecipientRepoService recipientRepoService, RecipientMapper recipientMapper) {
        this.recipientRepoService = recipientRepoService;
        this.recipientMapper = recipientMapper;
    }

    public List<RecipientRecordDTO> getAllRecipients() {
        return recipientRepoService.findAll().stream()
                .map(recipientMapper::toDto)
                .toList();
    }

    public RecipientRecordDTO addNewRecipient(Recipient recipient) {
        Recipient savedRecipient = recipientRepoService.save(recipient);
        return recipientMapper.toDto(savedRecipient);
    }

    public RecipientRecordDTO findRecipientById(Long id) {
        Recipient recipient = recipientRepoService.findById(id)
                .orElseThrow(() -> new IllegalStateException("Recipient with id " + id + " not found"));
        return recipientMapper.toDto(recipient);
    }

    public void deleteRecipientById(Long id) {
        boolean exists = recipientRepoService.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Recipient does not exist");
        }
        recipientRepoService.deleteById(id);
    }
}
