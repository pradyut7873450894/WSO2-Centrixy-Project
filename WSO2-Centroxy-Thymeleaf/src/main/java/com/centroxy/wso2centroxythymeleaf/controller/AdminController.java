package com.centroxy.wso2centroxythymeleaf.controller;


import com.centroxy.wso2centroxythymeleaf.entity.Group;
import com.centroxy.wso2centroxythymeleaf.entity.Userdetails;
import com.centroxy.wso2centroxythymeleaf.repository.UserRepository;
import com.centroxy.wso2centroxythymeleaf.service.GroupService;
import com.centroxy.wso2centroxythymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.List;


@Controller

@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepo;


    @Autowired
    private GroupService groupService;

    @GetMapping("/")
    public String home( Model model) {

    //   model.addAttribute("formVisible", false);
        return "admindashbord";
    }


    @GetMapping("/adminbyuser")
    public String register(Model model) {
        List<Group> groups = groupService.getAllGroups();
//model.addAttribute("formVisible", true);
         model.addAttribute("groups", groups);
        return "admin"; // Change this to the name of your HTML file
    }



    @PostMapping("/adminCreateUser")
    public String createUser(@ModelAttribute Userdetails user, @RequestParam("imageFile") MultipartFile imageFile,
                             HttpSession session, Model model) throws IOException {
        boolean f = userService.checkEmail(user.getEmail());

        if (f) {
            session.setAttribute("msg", "Email Id Already Exist..");
        } else {
            if (!imageFile.isEmpty()) {
                user.setImage(imageFile.getBytes());
            }
            List<Group> groups = groupService.getAllGroups();
            model.addAttribute("groups", groups);

            Userdetails userDtls = userService.createUser(user);

            if (userDtls != null) {
                session.setAttribute("msg", "Register Successfully");
            } else {
                session.setAttribute("msg", "Something went wrong on server...!!");
            }
        }

        return "redirect:/admin/adminbyuser";
    }
    
    
    //Adding for fetch user name
    @ModelAttribute
    private void userDetails(Model m, Principal p)
    {
        String email = p.getName();
        Userdetails user = userRepo.findByEmail(email);

        m.addAttribute("user", user);

    }
}

