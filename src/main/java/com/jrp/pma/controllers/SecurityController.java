package com.jrp.pma.controllers;

import com.jrp.pma.entities.UserAccount;
import com.jrp.pma.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class SecurityController {

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    UserAccountService userAccountService;

    @GetMapping({"/",""})
    public String register(Model model){
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount",userAccount);
        return "security/register";
    }

    @PostMapping("/save")
    public String saveUser(Model model, UserAccount user){
        user.setPassword(encoder.encode(user.getPassword()));
        userAccountService.save(user);
        return "redirect:/";
    }
}
