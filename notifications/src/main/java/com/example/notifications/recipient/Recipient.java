package com.example.notifications.recipient;

import com.example.notifications.notification.Notification;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "recipients")
public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipientId;
    private String name;
    private String email;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    public Long getId() {
        return recipientId;
    }
}
