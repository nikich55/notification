package com.example.notifications.notification_status;

import com.example.notifications.notification_status.enums.NotificationStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationStatusRepository  extends JpaRepository<NotificationStatus, Long> {
    NotificationStatus findByStatus(NotificationStatusEnum status);
}
