package com.example.myTriple.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Information {

    @Id @GeneratedValue// PK에 자동으로 숫자 부여해주기
    @Column(name = "Inform_id")
    private Long id; // PK

    public int members; // 인원수

    public int charge; // 경비

    public String location; // 여행지

    public String date; // 여행일


}
