package com.example.myTriple.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Getter @Setter @ToString
public class Member {

    @Id
    private Long id;
    private String name;
    private String password;
    private String email;
    private int like;


    public static void main(String[] args) {
        EntityManagerFactory myjpa = Persistence.createEntityManagerFactory("myunit");

        //Manager를 통해 Entitymanager 받아오기
        EntityManager entityManager = myjpa.createEntityManager();
        //트랜잭션 시작
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //member객체 생성 및 값 저장

        Member member = new Member();
        member.setId(1L);
        member.setName("admin");

        //persistence에 저장
        entityManager.persist(member);

        transaction.commit();
        entityManager.close();
        myjpa.close();

    }

}


