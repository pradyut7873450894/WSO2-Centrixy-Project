package com.centroxy.wso2centroxythymeleaf.controller;
import com.centroxy.wso2centroxythymeleaf.entity.Group;

import com.centroxy.wso2centroxythymeleaf.entity.Userdetails;
import com.centroxy.wso2centroxythymeleaf.service.GroupService;
import com.centroxy.wso2centroxythymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;



@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;





    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "register";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute Userdetails user, @RequestParam("imageFile") MultipartFile imageFile,
                             HttpSession session, Model model) throws IOException {
        boolean f = userService.checkEmail(user.getEmail());

        if (f) {
            session.setAttribute("msg", "Email Id Already Exist..");
        } else {
            if (!imageFile.isEmpty()) {
                user.setImage(imageFile.getBytes());
            }
//            List<Group> groups = groupService.getAllGroups();
//            model.addAttribute("groups", groups);

            Userdetails userDtls = userService.createUser(user);

            if (userDtls != null) {
                session.setAttribute("msg", "Register Successfully");
            } else {
                session.setAttribute("msg", "Something went wrong on server...!!");
            }
        }

        return "redirect:/register";
    }
    
    @GetMapping("/demo")
    public String demo()
    {
    	return "demo";
    }
}
