package com.example.desafiopicpay.repository;

import com.example.desafiopicpay.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCpfCnpj(String cpfCnpj);
    Optional<User> findByEmail(String email);
    User findUserById(Long id);
}
