package com.blanke.hanu.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    //TODO: demo

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String roleName;

    private String description;


}