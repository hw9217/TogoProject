package com.example.myTriple.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

//    @GetMapping("/user/signup") // 이렇게 요청이 들어오면
//    public String signup() {
//        return "Login/signup"; // 이 뷰파일을 리턴해주겟다는 말! login패키지내에 singup 파일
//    }



}
