package com.example.sweaterExp.controller;

import com.example.sweaterExp.domain.Role;
import com.example.sweaterExp.domain.User;
import com.example.sweaterExp.repo.IUserRepo;
import jdk.jfr.Enabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class RegistrationController {

    @Autowired
    private IUserRepo userRepo;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("messageRegistration", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRole(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }


}
