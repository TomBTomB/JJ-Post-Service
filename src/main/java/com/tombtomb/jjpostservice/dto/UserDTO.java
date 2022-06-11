package com.tombtomb.jjpostservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDTO {
    String username;

    UUID id;

    String displayName;

    String avatar;

    String bio;
}
