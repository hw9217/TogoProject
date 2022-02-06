package com.example.myTriple.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class EmailCheckToken {

    @Column
    private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L; // 토큰 만료 시간

    @Id
    @GeneratedValue(generator =  "uuid2")
    @GenericGenerator(name="uuid2", strategy ="uuid2")
    @Column(name = "id", nullable = false)
    private String id;

    @Column
    private LocalDateTime expirationDate;

    @Column
    private boolean expired;

    @Column
    private String userId;

    @CreatedDate
    @Column
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    /*
     이메일 인증 토큰 생성
     @param userID
     @return
     */
    public static EmailCheckToken createEmailToken(String userId) {
        EmailCheckToken emailCheckToken = new EmailCheckToken();
        emailCheckToken.expirationDate =LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE); // 5분후 만료
        emailCheckToken.userId = userId;
        emailCheckToken.expired = false;
        return emailCheckToken;

    }
    /*
    토큰만료
     */
    public void useToken() {
        expired = true;
    }


}
