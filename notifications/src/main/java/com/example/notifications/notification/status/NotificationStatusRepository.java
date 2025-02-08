package com.example.notifications.notification.status;

import com.example.notifications.notification.status.enums.NotificationStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationStatusRepository extends JpaRepository<NotificationStatus, Long> {
    NotificationStatus findByStatus(NotificationStatusEnum status);
}
