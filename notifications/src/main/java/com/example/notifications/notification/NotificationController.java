package com.example.notifications.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/notification")
public class NotificationController {
    private NotificationService notificationService;
    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // All notifications
    @GetMapping
    public List<NotificationDTO> getNotification() {
        return notificationService.getNotifications();
    }

   // Notifications by recipientId
    @GetMapping("/recipient/{recipientId}")
    public List<NotificationDTO> getNotificationsByRecipient(@PathVariable Long recipientId) {
        return notificationService.findRecipientById(recipientId);
    }

    // Unread notifications by id
    @GetMapping("/recipient/{recipientId}/unread")
    public List<NotificationDTO> getUnreadNotifications(@PathVariable Long recipientId) {
        return notificationService.findRecipientByIdAndStatus(recipientId);
    }

    // Send notification to recipient by id
    @PostMapping("/send/{recipientId}")
    public NotificationDTO sendNotificationToRecipient(@PathVariable Long recipientId, @RequestBody String message) {
       return notificationService.sendNotificationToRecipient(message, recipientId);
    }

    // Delete recipient by id
    @DeleteMapping(path = "/delete/{id}")
    public void deleteNotification(@PathVariable("id") Long id) {
        notificationService.deleteNotification(id);
    }
}
