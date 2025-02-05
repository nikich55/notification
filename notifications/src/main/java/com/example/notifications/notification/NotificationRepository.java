package com.example.notifications.notification;

import com.example.notifications.notification.Notification;
import com.example.notifications.notification_status.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientId(Long recipientId);
    List<Notification> findByRecipientIdAndStatus(Long recipientId, NotificationStatus status);
}
