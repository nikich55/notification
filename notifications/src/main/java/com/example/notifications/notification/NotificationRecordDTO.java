package com.example.notifications.notification;

import com.example.notifications.notification.status.enums.NotificationStatusEnum;

public record NotificationRecordDTO(
        String message,
        NotificationStatusEnum status,
        Long recipientId
) {
}
