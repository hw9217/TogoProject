package com.example.myTriple.Dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class MemberDto {
    private String email;
    private String password;
    private String name;
    private String phone;
    private String auth;
    private int like;
}
