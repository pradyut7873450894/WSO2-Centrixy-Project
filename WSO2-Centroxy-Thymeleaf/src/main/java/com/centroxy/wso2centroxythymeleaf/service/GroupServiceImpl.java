package com.centroxy.wso2centroxythymeleaf.service;


import com.centroxy.wso2centroxythymeleaf.entity.Group;
import com.centroxy.wso2centroxythymeleaf.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;


    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public void addGroup(Group group) {
        groupRepository.save(group);

    }



}

