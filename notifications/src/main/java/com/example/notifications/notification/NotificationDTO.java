package com.example.notifications.notification;

import com.example.notifications.notification_status.enums.NotificationStatusEnum;
import lombok.Data;

@Data
public class NotificationDTO {
    private String message;
    private NotificationStatusEnum status;
    private Long recipientId;


    public NotificationDTO() {

    }
    public NotificationDTO(Notification notification) {
        this.message = notification.getMessage();
        this.status = notification.getStatus().getStatus();
        this.recipientId = notification.getRecipient().getId();
    }
}
