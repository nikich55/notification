package com.example.notifications.recipient;

import com.example.notifications.notification.Notification;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "recipients")
public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipientId;

    private String name;
    private String email;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    public Recipient() {}

    public Recipient(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return recipientId; }
    public String getName() {
        return name; }
    public void setName(String name) {
        this.name = name; }
    public String getEmail() {
        return email; }
    public void setEmail(String email) {
        this.email = email; }
    public List<Notification> getNotifications() {
        return notifications; }
    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications; }
}
