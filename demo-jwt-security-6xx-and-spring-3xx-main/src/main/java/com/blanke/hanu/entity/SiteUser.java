package com.blanke.hanu.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "site_user")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email_address")
    private String email;


    @Column(name = "password")
    private String password;


    //TODO: apply role field
}
