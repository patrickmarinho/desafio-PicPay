package com.example.desafiopicpay.controller;

import com.example.desafiopicpay.domain.entity.user.User;
import com.example.desafiopicpay.dto.UserDTO;
import com.example.desafiopicpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping()
    public void createUser(@RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
        return;
    }
}
