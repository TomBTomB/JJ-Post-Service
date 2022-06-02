package com.tombtomb.jjpostservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ReplyDTO {
    private UUID id;

    private String body;

    private UUID userId;
}
