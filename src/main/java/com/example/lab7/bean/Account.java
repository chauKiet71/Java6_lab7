package com.example.lab7.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class Account {

    @Id
    String username;
    String password;
    String email;
    String fullname;
    @JsonIgnore
    @OneToMany(mappedBy = "authotirited")
    List<Authotirited> authotirited;
}
