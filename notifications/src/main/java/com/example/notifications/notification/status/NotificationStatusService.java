package com.example.notifications.notification.status;

import com.example.notifications.notification.status.enums.NotificationStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationStatusService {

    private final NotificationStatusRepoService notificationStatusRepoService;

    @Autowired
    public NotificationStatusService(NotificationStatusRepoService notificationStatusRepoService) {
        this.notificationStatusRepoService = notificationStatusRepoService;
    }

    public NotificationStatus getUnreadStatus() {
        return notificationStatusRepoService.findByStatus(NotificationStatusEnum.UNREAD);
    }

    public NotificationStatus getReadStatus() {
        return notificationStatusRepoService.findByStatus(NotificationStatusEnum.READ);
    }

    public NotificationStatus getFailedStatus() {
        return notificationStatusRepoService.findByStatus(NotificationStatusEnum.FAILED);
    }

    public NotificationStatus getSentStatus() {
        return notificationStatusRepoService.findByStatus(NotificationStatusEnum.SENT);
    }
}
