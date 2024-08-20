package com.example.desafiopicpay.service;

import com.example.desafiopicpay.domain.entity.user.User;
import com.example.desafiopicpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    //Post
    public User createUser(User user){
        Optional<User> existingUserCpfCnpjOptional = userRepository.findByCpfCnpj(user.getCpfCnpj());
        Optional<User> existingUserEmailOptional = userRepository.findByEmail(user.getEmail());

        if(existingUserCpfCnpjOptional.isPresent() || existingUserEmailOptional.isPresent()){
            throw new RuntimeException();
        } else {
            System.out.println(user.getPassword());
            userRepository.save(user);
        }
        return user;
    }
}
