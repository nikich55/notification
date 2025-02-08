package com.example.notifications.notification.status;

import com.example.notifications.notification.status.enums.NotificationStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationStatusRepoService {
    private final NotificationStatusRepository repository;

    @Autowired
    public NotificationStatusRepoService(NotificationStatusRepository repository) {
        this.repository = repository;
    }

    public NotificationStatus findByStatus(NotificationStatusEnum status) {
        return repository.findByStatus(status);
    }
}
