package com.example.notifications.notification;

import com.example.notifications.notification.status.NotificationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationRepoService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationRepoService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public List<Notification> findByRecipientId(Long recipientId) {
        return notificationRepository.findByRecipientId(recipientId);
    }

    public List<Notification> findByRecipientIdAndStatus(Long recipientId, NotificationStatus status) {
        return notificationRepository.findByRecipientIdAndStatus(recipientId, status);
    }

    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    public boolean existsById(Long id) {
        return notificationRepository.existsById(id);
    }

    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }
}