package com.centroxy.wso2centroxythymeleaf.controller;



import com.centroxy.wso2centroxythymeleaf.entity.Group;
import com.centroxy.wso2centroxythymeleaf.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;


    @GetMapping("/list")
    public String listGroups(Model model) {

        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "groups/list";
    }


    @GetMapping("/add")
    public String showGroupForm(Model model) {

        Group group = new Group();
        model.addAttribute("group", group);
        return "groups/form";
    }


    @PostMapping("/save")
    public String saveGroup(@ModelAttribute("group") Group group) {

        groupService.addGroup(group);
        return "redirect:/groups/list";
    }


}

