package com.example.myTriple.Repository;

import com.example.myTriple.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface MemberRepository extends JpaRepository<Member, Long> {
      boolean existsByEmail(String email);
      Member findByEmail(String email);
}
