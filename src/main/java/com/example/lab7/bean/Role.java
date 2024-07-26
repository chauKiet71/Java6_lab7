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
@Table(name = "Roles")
public class Role {

    @Id
    String id;
    String name;
    @JsonIgnore
    @OneToMany(mappedBy = "role")
    List<Authotirited> authotirited;
}
