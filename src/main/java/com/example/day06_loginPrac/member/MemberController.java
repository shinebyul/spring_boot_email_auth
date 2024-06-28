package com.example.day06_loginPrac.member;

import com.example.day06_loginPrac.verify.EmailVerityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final EmailVerityService emailVerityService;

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<String> signup(String email, String password){

        String uuid = memberService.sendEmail(email);

        memberService.signup(email,password);

        emailVerityService.create(email, uuid);


        return ResponseEntity.ok("회원 정보 저장 성공. 이메일 인증 필요");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/verify")
    public ResponseEntity<String> checkUuid(String email, String uuid){
        String result = emailVerityService.checkUuid(email, uuid);
        return ResponseEntity.ok(result);
    }


}
