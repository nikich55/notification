package com.example.notifications.recipient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipientRepoService {
    private final RecipientRepository repository;

    @Autowired
    public RecipientRepoService(RecipientRepository repository) {
        this.repository = repository;
    }

    public Optional<Recipient> findById(Long recipientId) {
        return repository.findById(recipientId);
    }

    public List<Recipient> findAll() {
        return repository.findAll();
    }

    public Recipient save(Recipient recipient) {
        return repository.save(recipient);
    }

    public boolean existsById(Long recipientId) {
        return repository.existsById(recipientId);
    }

    public void deleteById(Long recipientId) {
        repository.deleteById(recipientId);
    }
}
