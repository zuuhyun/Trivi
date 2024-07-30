package web.trivi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "birth", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date birth;

    @Column(name = "phone")
    private String phone;

    @Column(name = "triptype")
    private String triptype;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Column(name = "verification_yn")
    private Boolean verificationYn;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(columnDefinition = "TEXT")
    private String imgPath;

    @Builder
    public User(String email, String name, String uuid, String gender, Date birth, String phone, String triptype, String nickname, Boolean verificationYn, String password, String imgPath) {
        this.email = email;
        this.name = name;
        this.uuid = uuid;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
        this.triptype = triptype;
        this.nickname = nickname;
        this.verificationYn = verificationYn;
        this.createdAt = LocalDateTime.now();
        this.password = password;
        this.imgPath = imgPath;
    }

    // 기본 User 객체를 반환하는 정적 메서드 추가
    public static User createDefaultUser() {
        User defaultUser = new User();
        defaultUser.setImgPath(null);
        return defaultUser;
    }
}
