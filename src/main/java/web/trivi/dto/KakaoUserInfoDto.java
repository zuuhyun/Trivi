package web.trivi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfoDto {
    private String name;
    private String email;
    private String brithday;
    private String gender;

    public KakaoUserInfoDto(String name, String email, String brithday, String gender) {
        this.name = name;
        this.email = email;
        this.brithday = brithday;
        this.gender = gender;
    }
}