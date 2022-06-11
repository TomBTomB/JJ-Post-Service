package com.tombtomb.jjpostservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reply {

    @Id
    @GeneratedValue
    private UUID id;

    private String text;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;
}
