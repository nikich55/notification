package com.example.notifications.notification.sendnotification;

import com.example.notifications.notification.sendnotification.SendNotification;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class SendViberNotification implements SendNotification {

    @Override
    public void sendNotification(String message) {
        System.out.println("Viber notification: " + message);
    }
}
