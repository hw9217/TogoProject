package com.example.myTriple.Entity;

import lombok.*;


import javax.persistence.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity @NoArgsConstructor
@Getter @Setter @ToString
public class Member {

    @Id @Column
    @GeneratedValue
    private Long id;

    private String name;

    private String password;

    private String email;

    private String auth;

    private String phone;

    private int like;

    private  String emailCheckToken;

    private boolean emailVerified;

    private LocalDateTime joinedAt;




    @Builder
    private Member(String name, String  password, String email, int like, String auth, String phone) {
        this.name = name;
        this.password=password;
        this.email = email;
        this.like = like;
        this.auth = auth;
        this.phone = phone;

    }
    public boolean isValidToken(String token) {
        return token.equals(this.emailCheckToken);

    }

    public void completeSignup() {
    setEmailVerified(true);
    setJoinedAt(LocalDateTime.now());
    }


    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
    }
}


