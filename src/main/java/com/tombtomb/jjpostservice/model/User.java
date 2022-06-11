package com.tombtomb.jjpostservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "jjuser")
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private String username;

    private String displayName;

    @Builder.Default
    private String avatar = "/generic-avatar.jpg";

    private String bio;
}
