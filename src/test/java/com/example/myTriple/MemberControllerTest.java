package com.example.myTriple;

import com.example.myTriple.Entity.Member;
import com.example.myTriple.Mail.ConsoleMailSender;
import com.example.myTriple.Repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @MockBean
    private ConsoleMailSender consoleMailSender;

    @DisplayName("회원가입 화면 보이는지 테스트")
    @Test
    void signupForm() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("/user/signup"))
                .andExpect(model().attributeExists("signupForm"));
    }

    @DisplayName("회원가입 입력값 오류")
    @Test
    void signupSubmit_with_wrong_input() throws Exception {
        mockMvc.perform(post("/signup")
                .param("email", "aa") // 잘못된 이메일 형식
                .param("password", "admin1234")
                .param("name"," ")
                .param("zipcode"," ")
                //약관 동의 체크 안함
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("/Login/signUp"));

    }

    @DisplayName("회원 가입 - 입력값 정상")
    @Test
    void signupSubmit_with_correct_input() throws Exception{
        mockMvc.perform(post("/signup")
                        .param("email", "admin@test.com")  // 올바른 이메일 형식
                        .param("password", "admin1234")
                        .param("name", "홍길동")
                        .param("city", "고양시")
                        .param("zipcode", "몰라")
                        .param("agreeTermsOfService", "true")// 약관 동의도 체크 함
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));


        Member member = memberRepository.findByEmail("admin@test.com");
        assertNotNull(member);
        assertNotEquals(member.getPassword(),"1234");

        // 실제 디비에 들어갔는지도 확인
        assertTrue(memberRepository.existsByEmail("admin@test.com"));

        // 메일 송신 (실제 송신은 아님) send()가 호출 되었는지 확인
        then(consoleMailSender).should().send(any(SimpleMailMessage.class));

    }




}
