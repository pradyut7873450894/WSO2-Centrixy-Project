package com.centroxy.wso2centroxythymeleaf.service;

import com.centroxy.wso2centroxythymeleaf.entity.Group;
import com.centroxy.wso2centroxythymeleaf.entity.Userdetails;


public interface UserService {


    public Userdetails createUser(Userdetails user);

    public boolean checkEmail(String email);



}

