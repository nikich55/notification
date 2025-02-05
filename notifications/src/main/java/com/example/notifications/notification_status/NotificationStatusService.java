package com.example.notifications.notification_status;

import com.example.notifications.notification_status.enums.NotificationStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationStatusService {

    private final NotificationStatusRepository notificationStatusRepository;

    @Autowired
    public NotificationStatusService(NotificationStatusRepository notificationStatusRepository) {
        this.notificationStatusRepository = notificationStatusRepository;
    }

    public NotificationStatus getUnreadStatus() {
        return notificationStatusRepository.findByStatus(NotificationStatusEnum.UNREAD);
    }

    public NotificationStatus getReadStatus() {
        return notificationStatusRepository.findByStatus(NotificationStatusEnum.READ);
    }

    public NotificationStatus getFailedStatus() {
        return notificationStatusRepository.findByStatus(NotificationStatusEnum.FAILED);
    }

    public NotificationStatus getSentStatus() {
        return notificationStatusRepository.findByStatus(NotificationStatusEnum.SENT);
    }
}
