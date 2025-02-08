package com.example.notifications.notification;

import com.example.notifications.notification.status.NotificationStatus;
import com.example.notifications.recipient.Recipient;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private NotificationStatus status;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Recipient recipient;
}

