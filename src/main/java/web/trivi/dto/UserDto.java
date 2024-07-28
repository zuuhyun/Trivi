package web.trivi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import web.trivi.domain.User;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String email;

    private String name;

    private String gender;

    private Date birth;

    private String phone;

    private String triptype;

    private String nickname;

    private Boolean verificationYn;

    private LocalDateTime createdAt;

    private String imgPath;

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .name(this.name)
                .gender(this.gender)
                .birth(this.birth)
                .phone(this.phone)
                .triptype(this.triptype)
                .nickname(this.nickname)
                .verificationYn(this.verificationYn)
                .imgPath(this.imgPath)
                .build();
    }
}
