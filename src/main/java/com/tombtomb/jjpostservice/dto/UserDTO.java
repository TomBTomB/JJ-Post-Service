package com.tombtomb.jjpostservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    String username;

    @Builder.Default
    int id = 1;

    String displayName;

    @Builder.Default
    String avatar = "/generic-avatar.jpg";
}
