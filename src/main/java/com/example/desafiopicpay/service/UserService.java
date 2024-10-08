package com.example.desafiopicpay.service;

import com.example.desafiopicpay.domain.entity.user.User;
import com.example.desafiopicpay.dto.UserDTO;
import com.example.desafiopicpay.exceptions.UserAlreadyExistsException;
import com.example.desafiopicpay.exceptions.UserNegativeBalanceException;
import com.example.desafiopicpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    //Post
    public void createUser(UserDTO userDTO){
        Optional<User> existingUserCpfCnpjOptional = userRepository.findByCpfCnpj(userDTO.cpfCnpj());
        Optional<User> existingUserEmailOptional = userRepository.findByEmail(userDTO.email());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);

        if(existingUserCpfCnpjOptional.isEmpty() && existingUserEmailOptional.isEmpty()){
            String encodedCpfCnpj = encoder.encode(userDTO.cpfCnpj());
            String encodedPassword  = encoder.encode(userDTO.password());
            User user = new User(userDTO);
            user.setCpfCnpj(encodedCpfCnpj);
            user.setPassword(encodedPassword);

            if(userDTO.currentBalance().compareTo(BigDecimal.valueOf(0)) < 0){
                throw new UserNegativeBalanceException();
            }
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException();
        }
    }
}
