package com.example.notifications.notification.sendnotification;

import org.springframework.stereotype.Service;

@Service
public class SendMessengerNotification implements SendNotification {
    @Override
    public void sendNotification(String message) {
        System.out.println("Messenger notification: " + message);
    }
}
