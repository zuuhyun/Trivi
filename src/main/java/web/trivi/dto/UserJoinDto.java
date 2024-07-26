package web.trivi.dto;

//import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.trivi.domain.User;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserJoinDto {

//    @NotBlank
    private String email;

//    @NotBlank
    private String password;

//    @NotBlank
    private String name;

//    @NotBlank
    private Date birth;

//    @NotBlank
    private String gender;

//    @NotBlank
    private String nickname;

//    @NotBlank
    private String imgPath;

//    @NotBlank
    private String triptype;

    // 비밀번호 암호화 X
    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .birth(this.birth)
                .gender(this.gender)
                .nickname(this.nickname)
                .imgPath(this.imgPath)
                .triptype(this.triptype)
                .build();
    }

    // 비밀번호 암호화
    public User toEntity(String encodedPassword) {
        return User.builder()
                .email(this.email)
                .password(encodedPassword)
                .name(this.name)
                .birth(this.birth)
                .gender(this.gender)
                .nickname(this.nickname)
                .imgPath(this.imgPath)
                .triptype(this.triptype)
                .build();
    }

}
