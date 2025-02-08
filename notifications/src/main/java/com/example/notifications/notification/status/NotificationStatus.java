package com.example.notifications.notification.status;

import com.example.notifications.notification.status.enums.NotificationStatusEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "notification_status")
public class NotificationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationStatusEnum status;   // READ, UNREAD, SENT, FAILED

    public Long getId() {
        return id;
    }

    public NotificationStatusEnum getStatus() {
        return status;
    }

    public void setStatus(NotificationStatusEnum status) {
        this.status = status;
    }
}
