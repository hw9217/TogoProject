package com.example.myTriple.Controller;

import com.example.myTriple.Entity.Member;
import com.example.myTriple.Mail.ConsoleMailSender;
import com.example.myTriple.Repository.MemberRepository;
import com.example.myTriple.Service.MemberService;
import com.example.myTriple.form.SignupForm;
import com.example.myTriple.form.SignupFormValidator;
import lombok.RequiredArgsConstructor;
import org.h2.engine.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RequiredArgsConstructor // TODO 용어 정리 이 어노테이션은 초기화되지 않은 final 필드나 @NOtnull이 붙은 필드에 생성자를 생성 해줌
@Controller
public class MemberController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final MemberRepository memberRepository;

    private final MemberService memberService;

    private final SignupFormValidator signupFormValidator;


    @GetMapping("/user/signup") //TODO 뷰 페이지에 날라가는 파라미터 값 수정
    public String signup(Model model) {
        model.addAttribute(/*"signupForm"*/ new SignupForm());
        return "Login/signup";
    }

    @PostMapping("/user/signup")
    public String signupSubmit(@Valid SignupForm signupForm, Errors errors) {
        if (errors.hasErrors()) {
            logger.info("실패");
            return "/Login/signUp";
        }
        logger.info("성공");
        memberService.processNewMember(signupForm);
        return "redirect:/";
    }


    @GetMapping("/user/login")
    public String login() {
        return "/Login/login";
    }

    @GetMapping("/check-email-token")
    @Transactional
    public String checkEmailToken(String token, String email, Model model){
        Member member = memberRepository.findByEmail(email);
        if(member==null) {
            model.addAttribute("error" , "wrong-email");
            return "user/checked-email";
        }
        if(!member.getEmailCheckToken().equals(token)) {
            model.addAttribute("error", "wrong-email");
            return "user/checked-email";
        }
        member.completeSignup();
        model.addAttribute("email", member.getEmail());
        return "user/checked-email";

    }


}


