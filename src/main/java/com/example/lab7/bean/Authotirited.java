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
    int id;
    @ManyToOne
    @JoinColumn(name = "username")
    Account authotirited;
    @ManyToOne
    @JoinColumn(name = "roleid")
    Role role;
}
