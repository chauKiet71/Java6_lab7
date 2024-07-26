package com.example.lab7.bean;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Authorities")
public class Authotirited {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "username")
    Account account;
    @ManyToOne
    @JoinColumn(name = "roleid")
    Role role;
}
