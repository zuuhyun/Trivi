package web.trivi.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.type.YesNoConverter;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "birth", nullable = false)
    private Date birth;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "triptype", nullable = false)
    private String triptype;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "verification_yn", nullable = false)
    private Boolean verificationYn;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public User(String email, String name, String uuid, String gender, Date birth, String phone, String triptype, String nickname, Boolean verificationYn ) {
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
    }
}
