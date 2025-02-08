package com.example.notifications.notification;

import com.example.notifications.notification.sendnotification.SendNotification;
import com.example.notifications.notification.status.NotificationStatusService;
import com.example.notifications.recipient.Recipient;
import com.example.notifications.recipient.RecipientRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final SendNotification sendNotification;
    private final NotificationRepoService notificationRepoService;
    private final RecipientRepoService recipientRepoService;
    private final NotificationStatusService notificationStatusService;
    private final NotificationMapper notificationMapper;


    @Autowired
    //@Qualifier("sendMessengerNotification")
    public NotificationService(SendNotification sendNotification, NotificationRepoService notificationRepoService,
                               RecipientRepoService recipientRepoService, NotificationStatusService notificationStatusService,
                               NotificationMapper notificationMapper) {
        this.sendNotification = sendNotification;
        this.notificationRepoService = notificationRepoService;
        this.recipientRepoService = recipientRepoService;
        this.notificationStatusService = notificationStatusService;
        this.notificationMapper = notificationMapper;
    }

    public List<NotificationRecordDTO> getNotifications() {
        return convertToDTOList(notificationRepoService.findAll());
    }

    public List<NotificationRecordDTO> findRecipientById(Long recipientId) {
        return convertToDTOList(notificationRepoService.findByRecipientId(recipientId));
    }

    public List<NotificationRecordDTO> getUnreadNotifications(Long recipientId) {
        return convertToDTOList(notificationRepoService.findByRecipientIdAndStatus(recipientId, notificationStatusService.getUnreadStatus()));
    }

    public NotificationRecordDTO sendNotificationToRecipient(String message, Long recipientId) {
        Recipient recipient = recipientRepoService.findById(recipientId)
                .orElseThrow(() -> new IllegalStateException("Recipient not found"));

        Notification notification = createNotification(message, recipient);
        sendNotification.sendNotification(message);
        Notification savedNotification = notificationRepoService.save(notification);

        return notificationMapper.toDto(savedNotification);
    }

    private Notification createNotification(String message, Recipient recipient) {
        Notification notification = new Notification();

        notification.setMessage(message);
        notification.setRecipient(recipient);
        notification.setStatus(notificationStatusService.getUnreadStatus());

        return notification;
    }

    public void deleteNotification(Long id) {
        boolean exists = notificationRepoService.existsById(id);
        if (!exists) {
            throw new IllegalStateException("notification does not exist");
        }
        notificationRepoService.deleteById(id);
    }

    private List<NotificationRecordDTO> convertToDTOList(List<Notification> notifications) {
        return notifications.stream()
                .map(notificationMapper::toDto)
                .toList();
    }
}
