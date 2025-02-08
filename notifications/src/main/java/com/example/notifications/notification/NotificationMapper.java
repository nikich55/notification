package com.example.notifications.notification;

import com.example.notifications.notification.status.NotificationStatus;
import com.example.notifications.notification.status.enums.NotificationStatusEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    @Mapping(source = "recipient.recipientId", target = "recipientId")
    NotificationRecordDTO toDto(Notification notification);

    default NotificationStatusEnum map(NotificationStatus status) {
        if (status == null) {
            return null;
        }
        return NotificationStatusEnum.valueOf(status.getStatus().toString());
    }
}
