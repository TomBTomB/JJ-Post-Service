package com.tombtomb.jjbackend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PostDTO {

    private UUID id;

    private String body;

}
