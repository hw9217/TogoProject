package com.example.myTriple.Service;

import com.example.myTriple.Entity.Member;
import com.example.myTriple.Repository.MemberRepository;
import com.example.myTriple.form.SignupForm;
import com.example.myTriple.form.SignupFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private SignupFormValidator signupFormValidator;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @InitBinder("SignupForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signupFormValidator); // TODO 추가했음
    }

    @Transactional
    public void processNewMember(SignupForm signupForm) {
        Member newMember = saveNewMember(signupForm);
        newMember.generateEmailCheckToken(); // 이메일 토큰 생성 및 필드 저장
        sendSignupConfirmEmail(newMember);
    }


    private Member saveNewMember(@Valid  SignupForm signupForm) {
        Member member = Member.builder()
                .email(signupForm.getEmail())
                .password(passwordEncoder.encode(signupForm.getPassword()))
                .name(signupForm.getName())
                .phone(signupForm.getPhone())
                .build();
        Member newMember = memberRepository.save(member);
        return newMember;
    }

    private void sendSignupConfirmEmail(Member newMember) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(newMember.getEmail());
        simpleMailMessage.setSubject("TOgo 회원가입 인증");
        simpleMailMessage.setText("/check-email-token?token="+
                newMember.getEmailCheckToken()
                + "&email=" + newMember.getEmail());
        javaMailSender.send(simpleMailMessage);
    }


    public void login(Member member) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                member.getEmail(), member.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);
    }
}
