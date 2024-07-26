package com.example.lab7.dao;

import com.example.lab7.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesDao extends JpaRepository<Role, String> {
}
