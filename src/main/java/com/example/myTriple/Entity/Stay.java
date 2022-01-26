package com.example.myTriple.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Stay {
    @Id @GeneratedValue
    @Column(name = "Stay_id")
    private Long id;

    private String name; // 숙박 시설 이름

    private String address; // 숙박 시설 위치

}
