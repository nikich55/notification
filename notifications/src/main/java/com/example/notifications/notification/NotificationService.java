package com.example.notifications.notification;

import com.example.notifications.notification.Notification;
import com.example.notifications.recipient.Recipient;
import com.example.notifications.notification.enums.NotificationStatus;
import com.example.notifications.notification.sendnotification.SendNotification;
import com.example.notifications.notification.NotificationRepository;
import com.example.notifications.recipient.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final SendNotification sendNotification;

    private final NotificationRepository notificationRepository;

    private final RecipientRepository recipientRepository;

    @Autowired
    //@Qualifier("sendMessengerNotification")
    public NotificationService(SendNotification sendNotification, NotificationRepository notificationRepository,
                               RecipientRepository recipientRepository) {
        this.sendNotification = sendNotification;
        this.notificationRepository = notificationRepository;
        this.recipientRepository = recipientRepository;
    }

    public List<NotificationDTO> getNotifications() {
        return convertToDTOList(notificationRepository.findAll());
    }

    public List<NotificationDTO> findRecipientById(Long recipientId) {
        return convertToDTOList(notificationRepository.findByRecipientId(recipientId));
    }

    public List<NotificationDTO> findRecipientByIdAndStatus(Long recipientId) {
        if (!recipientRepository.existsById(recipientId)) {
            throw new IllegalStateException("Recipient with id " + recipientId + " not found");
        }
        return convertToDTOList(notificationRepository.findByRecipientIdAndStatus(recipientId, NotificationStatus.UNREAD));
    }

    public NotificationDTO sendNotificationToRecipient(String message, Long recipientId) {
        Recipient recipient = recipientRepository.findById(recipientId)
                .orElseThrow(() -> new IllegalStateException("Recipient not found"));

        Notification notification = createNotification(message, recipient);
        sendNotification.sendNotification(message);
        Notification savedNotification = notificationRepository.save(notification);

        return new NotificationDTO(savedNotification);
    }
    private Notification createNotification(String message, Recipient recipient) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setRecipient(recipient);
        notification.setStatus(NotificationStatus.UNREAD);
        return notification;
    }
    public void deleteNotification(Long id) {
        boolean exists = notificationRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("notification does not exist");
        }
        notificationRepository.deleteById(id);
    }

    private List<NotificationDTO> convertToDTOList(List<Notification> notifications) {
        return notifications.stream()
                .map(NotificationDTO::new)
                .toList();
    }
}
