package com.example.myTriple.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/user/login")
    public String login() {
        return "/Login/login";
    }
    
    @GetMapping("/user/signup") 
        public String signup() {
            return "/Login/signUp"; // TODO 회원가입 뷰 구현
        }
    }

