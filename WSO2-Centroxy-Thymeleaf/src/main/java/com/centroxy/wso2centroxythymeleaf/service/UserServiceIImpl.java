package com.centroxy.wso2centroxythymeleaf.service;

import com.centroxy.wso2centroxythymeleaf.entity.Group;
import com.centroxy.wso2centroxythymeleaf.entity.Userdetails;

import com.centroxy.wso2centroxythymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceIImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private GroupService groupService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Userdetails createUser(Userdetails user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        List<Group> groups =groupService.getAllGroups();

        return userRepo.save(user);
    }




    @Override
    public boolean checkEmail(String email) {


        return userRepo.existsByEmail(email);
    }



}

