package com.centroxy.wso2centroxythymeleaf.config;



import com.centroxy.wso2centroxythymeleaf.entity.Userdetails;

import com.centroxy.wso2centroxythymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Userdetails user= userRepo.findByEmail(username);

        if(user!=null)
        {
            return new CustomUserDetails(user);
        }

        throw new UsernameNotFoundException("user not avlable");
    }

}
