package com.example.day06_loginPrac.verify;

import com.example.day06_loginPrac.member.MemberRepository;
import com.example.day06_loginPrac.member.model.Member;
import com.example.day06_loginPrac.verify.model.EmailVerify;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailVerityService {
    private final EmailVerifyRepository emailVerifyRepository;
    private final MemberRepository memberRepository;

    public String checkUuid(String email, String uuid){
        Optional<EmailVerify> result =  emailVerifyRepository.findByEmailAndUuid(email, uuid);

        if(result.isPresent()){
            Optional<Member> res = memberRepository.findByEmail(email);
            Member member = res.get();
            Member newMember = Member.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .password(member.getPassword())
                    .active(true)
                    .build();
            memberRepository.save(newMember);
        }else{
            return "이메일 인증에 실패했습니다.";
        }
        return "이메일 인증에 성공했습니다.";
    }

    public void create(String email, String uuid) {
        EmailVerify emailVerify = EmailVerify.builder()
                .email(email)
                .uuid(uuid)
                .build();

        emailVerifyRepository.save(emailVerify);
    }
}
