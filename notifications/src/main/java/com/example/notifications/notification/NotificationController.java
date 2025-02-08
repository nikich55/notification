package com.example.notifications.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // All notifications
    @GetMapping
    public List<NotificationRecordDTO> getNotification() {
        return notificationService.getNotifications();
    }

    // Notifications by recipientId
    @GetMapping("/recipient/{recipientId}")
    public List<NotificationRecordDTO> getNotificationsByRecipient(@PathVariable Long recipientId) {
        return notificationService.findRecipientById(recipientId);
    }

    // Unread notifications by id
    @GetMapping("/recipient/{recipientId}/unread")
    public List<NotificationRecordDTO> getUnreadNotifications(@PathVariable Long recipientId) {
        return notificationService.getUnreadNotifications(recipientId);
    }

    // Send notification to recipient by id
    @PostMapping("/send/{recipientId}")
    public NotificationRecordDTO sendNotificationToRecipient(@PathVariable Long recipientId, @RequestBody NotificationsRequest request) {
        return notificationService.sendNotificationToRecipient(request.message(), recipientId);
    }

    // Delete recipient by id
    @DeleteMapping(path = "/delete/{id}")
    public void deleteNotification(@PathVariable("id") Long id) {
        notificationService.deleteNotification(id);
    }
}
