package com.tombtomb.jjpostservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ReplyCreateDTO {

    private String body;

    private UUID userId;

}
