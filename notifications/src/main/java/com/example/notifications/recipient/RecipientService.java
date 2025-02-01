package com.example.notifications.recipient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipientService {
    private final RecipientRepository recipientRepository;

    @Autowired
    public RecipientService(RecipientRepository recipientRepository) {
        this.recipientRepository = recipientRepository;
    }

    public List<RecipientDTO> getAllRecipients() {
        return recipientRepository.findAll().stream()
                .map(recipient -> new RecipientDTO(recipient))
                .toList();
    }

    public RecipientDTO addNewRecipient(Recipient recipient) {
        Recipient savedRecipient = recipientRepository.save(recipient);
        return new RecipientDTO(savedRecipient);
    }

    public RecipientDTO findRecipientById(Long id) {
        Recipient recipient = recipientRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Recipient with id " + id + " not found"));
        return new RecipientDTO(recipient);
    }

    public void deleteRecipientById(Long id) {
        boolean exists = recipientRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Recipient does not exist");
        }
        recipientRepository.deleteById(id);
    }
}
