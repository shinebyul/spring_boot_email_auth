package com.example.day06_loginPrac.member;

import com.example.day06_loginPrac.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JavaMailSender emailSender;

    public void signup(String email, String password){
        Member member = Member.builder()
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .active(false)
                .build();
        memberRepository.save(member);
    }

    public String sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[내사이트] 가입 환영");

        String uuid = UUID.randomUUID().toString();

        message.setText("http://localhost/api/member/verify?email="+email+"&uuid="+uuid);

        emailSender.send(message);

        return uuid;
    }
}
