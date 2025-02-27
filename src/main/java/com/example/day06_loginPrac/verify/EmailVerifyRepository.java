package com.example.day06_loginPrac.verify;

import com.example.day06_loginPrac.verify.model.EmailVerify;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerifyRepository extends JpaRepository<EmailVerify, Long> {
    Optional<EmailVerify> findByEmailAndUuid(String email, String uuid);
}
