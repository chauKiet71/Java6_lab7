package com.example.lab7.dao;

import com.example.lab7.bean.Authotirited;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesDao extends JpaRepository<Authotirited, Integer> {
}
