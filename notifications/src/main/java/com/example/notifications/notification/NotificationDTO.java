package com.example.notifications.notification;

import com.example.notifications.notification.enums.NotificationStatus;
import lombok.Data;

@Data
public class NotificationDTO {
    private String message;
    private NotificationStatus status;
    private Long recipientId;


    public NotificationDTO() {

    }
    public NotificationDTO(Notification notification) {
        this.message = notification.getMessage();
        this.status = notification.getStatus();
        this.recipientId = notification.getRecipient().getId();
    }
}
