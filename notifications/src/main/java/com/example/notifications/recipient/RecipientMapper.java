package com.example.notifications.recipient;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecipientMapper {

    RecipientMapper INSTANCE = Mappers.getMapper(RecipientMapper.class);

    RecipientRecordDTO toDto(Recipient recipient);
}
