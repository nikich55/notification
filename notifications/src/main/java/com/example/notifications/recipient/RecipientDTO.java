package com.example.notifications.recipient;

import lombok.Data;

@Data
public class RecipientDTO {
    private Long recipientId;
    private String name;
    private String email;

    public RecipientDTO() {

    }

    public RecipientDTO(Recipient recipient) {
        this.recipientId = recipient.getId();
        this.name = recipient.getName();
        this.email = recipient.getEmail();;
    }
}
