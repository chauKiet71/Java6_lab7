package com.example.lab7.controller;

import com.example.lab7.bean.Authotirited;
import com.example.lab7.dao.AccountsDao;
import com.example.lab7.dao.AuthoritiesDao;
import com.example.lab7.dao.RolesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class AuthorityRestController {

    @Autowired
    AuthoritiesDao authoritiesDao;

    @Autowired
    RolesDao rolesDao;

    @Autowired
    AccountsDao accountsDao;

    @GetMapping("/rest/authorities")
    public Map<String, Object> getAuthorities() {
        Map<String, Object> map = new HashMap<>();
        map.put("authorities", authoritiesDao.findAll());
        map.put("roles", rolesDao.findAll());
        map.put("accounts", accountsDao.findAll());
        return map;
    }
    @PostMapping("/rest/authorities")
    public Authotirited create(@RequestBody Authotirited authotirited) {
        return authoritiesDao.save(authotirited);
    }

    @DeleteMapping("/rest/authorities/{id}")
    public void deleteAuthorities(@PathVariable("id") Integer id) {
        authoritiesDao.deleteById(id);
    }
}
