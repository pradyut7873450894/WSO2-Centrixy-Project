package com.centroxy.wso2centroxythymeleaf.repository;


import com.centroxy.wso2centroxythymeleaf.entity.Userdetails;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Userdetails, Integer> {

    public boolean existsByEmail(String email);

    public Userdetails findByEmail(String email);
}