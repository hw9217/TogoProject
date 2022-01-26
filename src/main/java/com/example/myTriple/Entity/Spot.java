package com.example.myTriple.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


//Spot Entity : 여행지중 인기 있는 스팟들을 저장하고 갖다 쓸 entity
@Entity
@Getter @Setter
public class Spot {


    @Id @GeneratedValue
    @Column(name = "Spot_id")
    private Long id;

    private String address; // 여행지 주소



}
