package com.example.lab7.dao;

import com.example.lab7.bean.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AccountsDao extends JpaRepository<Account, String> {
}
