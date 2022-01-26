package com.example.myTriple.Config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    public void configure(WebSecurity sec) throws Exception {
//        sec.ignoring().antMatchers("/css/**", "/js/**", "/assets/**", "/images/**");
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    public void configure(HttpSecurity http) throws  Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").permitAll()
                .mvcMatchers("/", "login","/signup", "/check-email", "/check-email-token").permitAll()
                //다음 목록은 get요청만 가능
                .mvcMatchers(HttpMethod.GET, "/item/*").permitAll() //TODO item은 다른걸로 수정하기
                //나머지 요청은 로그인 해야지만 가능
                .anyRequest().authenticated();
    }


}
