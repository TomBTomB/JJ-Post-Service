package com.tombtomb.jjpostservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class PostCreateDTO {

    @NotNull
    @Size(min = 1, max = 200, message = "Body must be between 1 and 200 characters")
    private String body;

    @NotNull
    private UUID userId;

}