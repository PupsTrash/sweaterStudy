package com.example.sweaterExp.controller;

import com.example.sweaterExp.domain.Role;
import com.example.sweaterExp.domain.User;
import com.example.sweaterExp.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('GOD')")
public class UserController {
    @Autowired
    private IUserRepo userRepo;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String editUserForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @PostMapping
    public String userService(@RequestParam("userID") User user,
                              @RequestParam Map<String, String> form,
                              @RequestParam String username)
    {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRole().clear();             //X3
        for (String key : form.keySet()) { //X3x2
            if (roles.contains(key)) {
                user.getRole().add(Role.valueOf(key));
            }
        }


        userRepo.save(user);
        return "redirect:/user/";
    }

}
