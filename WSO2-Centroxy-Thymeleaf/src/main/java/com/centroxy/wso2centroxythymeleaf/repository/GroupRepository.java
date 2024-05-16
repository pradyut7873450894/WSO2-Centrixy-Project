package com.centroxy.wso2centroxythymeleaf.repository;

import com.centroxy.wso2centroxythymeleaf.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Long> {
    //Group findByUsername(String username);


}
