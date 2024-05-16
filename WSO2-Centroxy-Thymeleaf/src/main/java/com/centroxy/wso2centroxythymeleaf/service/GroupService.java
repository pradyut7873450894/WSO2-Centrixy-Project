package com.centroxy.wso2centroxythymeleaf.service;


import com.centroxy.wso2centroxythymeleaf.entity.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {

    List<Group> getAllGroups();
     void addGroup(Group group);

}