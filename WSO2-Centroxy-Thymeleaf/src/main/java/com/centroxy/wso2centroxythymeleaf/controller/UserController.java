package com.centroxy.wso2centroxythymeleaf.controller;



import java.security.Principal;

import com.centroxy.wso2centroxythymeleaf.entity.Userdetails;

import com.centroxy.wso2centroxythymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/")
    public String home()
    {
        return "user";
    }



    @ModelAttribute
    private void userDetails(Model m, Principal p)
    {
        String email = p.getName();
        Userdetails user = userRepo.findByEmail(email);

        m.addAttribute("user", user);

    }
}
