package com.tombtomb.jjpostservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ReplyDTO {
    private UUID id;

    private String text;

    private UserDTO user;
}
