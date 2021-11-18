package com.jrp.pma.services;


import com.jrp.pma.entities.UserAccount;
import com.jrp.pma.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    public UserAccount save(UserAccount user){
        return userAccountRepository.save(user);
    }
}
