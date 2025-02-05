package com.example.notifications.notification;

import com.example.notifications.notification.sendnotification.SendNotification;
import com.example.notifications.notification_status.NotificationStatusService;
import com.example.notifications.recipient.Recipient;
import com.example.notifications.recipient.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final SendNotification sendNotification;

    private final NotificationRepository notificationRepository;

    private final RecipientRepository recipientRepository;

    private final NotificationStatusService notificationStatusService;

    @Autowired
    //@Qualifier("sendMessengerNotification")
    public NotificationService(SendNotification sendNotification, NotificationRepository notificationRepository,
                               RecipientRepository recipientRepository, NotificationStatusService notificationStatusService) {
        this.sendNotification = sendNotification;
        this.notificationRepository = notificationRepository;
        this.recipientRepository = recipientRepository;
        this.notificationStatusService = notificationStatusService;
    }

    public List<NotificationDTO> getNotifications() {
        return convertToDTOList(notificationRepository.findAll());
    }

    public List<NotificationDTO> findRecipientById(Long recipientId) {
        return convertToDTOList(notificationRepository.findByRecipientId(recipientId));
    }

    public List<NotificationDTO> getUnreadNotifications(Long recipientId) {
        return convertToDTOList(notificationRepository.findByRecipientIdAndStatus(recipientId, notificationStatusService.getUnreadStatus()));
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
        notification.setStatus(notificationStatusService.getUnreadStatus());

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
