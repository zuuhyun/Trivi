package web.trivi.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.trivi.domain.User;
import web.trivi.dto.UserJoinDto;
import web.trivi.dto.UserLoginDto;
import web.trivi.service.UserService;

import java.util.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        boolean emailExists = userService.checkEmailExists(email);
        return ResponseEntity.ok(emailExists);
    }

    @GetMapping("/check-nickname")
    public ResponseEntity<Boolean> checkNickname(@RequestParam String nickname){
        boolean nicknameExists = userService.checkNicknameExists(nickname);
        return ResponseEntity.ok(nicknameExists);
    }

    // /api/v1/users/join
    @PostMapping("/join")
    public boolean join(@RequestBody UserJoinDto joinRequest){

        //이메일 중복 체크
        if(userService.checkEmailExists(joinRequest.getEmail())){
            return false;
        }

        //닉네임 중복 체크
        if(userService.checkNicknameExists(joinRequest.getNickname())){
            return false;
        }

        userService.joinPwEncoded(joinRequest);

        return true;
    }

    // /api/v1/users/login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserLoginDto loginRequest, HttpServletRequest httpServletRequest){

        User user = userService.login(loginRequest);

        if(user==null) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Invalid username or password.");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // 로그인 성공시 기존의 세션 파기하고 새로운 세션 생성
        httpServletRequest.getSession().invalidate();
        HttpSession session = httpServletRequest.getSession(true);

        //세선 30분동안 유지
        session.setMaxInactiveInterval(1800);

        // 응답 put - 세션아이디, 이메일, 유저아이디
        Map<String, Object> response = new HashMap<>();
        response.put("session-id", session.getId());
        response.put("user-email", user.getEmail());
        response.put("user-id", user.getId());

        return ResponseEntity.ok(response);
    }

    //프론트에서 세션 정보 보내줄 예정 -> 받아서 세션 죽이기 
    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout(HttpServletRequest request) {

        // 세션이 없으면 return null
        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        return ResponseEntity.ok(true);
    }

    @GetMapping("/mypage/user-email/{user-email}")
    public ResponseEntity<User> getMypage(@PathVariable("user-email") String userEmail){

        User user = userService.getUserByEmail(userEmail);

        // 사용자 존재 여부 체크
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);

    }

    @PostMapping("/onboard/user-email/{user-email}/onboard/{onboard}")
    public ResponseEntity<Boolean> setOnboard(@PathVariable("user-email") String userEmail,
                                     @PathVariable("onboard") String onboard){

        boolean isTriptypeUpdate = userService.modifyTriptype(onboard, userEmail);

        if(isTriptypeUpdate){
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);  // 실패한 경우
        }

    }


/*
    public static Hashtable sessionList = new Hashtable();

    @GetMapping("/session-list")
    @ResponseBody
    public Map<String, String> sessionList() {
        Enumeration elements = sessionList.elements();

        Map<String, String> lists = new HashMap<>();

        while(elements.hasMoreElements()) {
            HttpSession session = (HttpSession)elements.nextElement();
            lists.put(session.getId(), "test");
        }
        return lists;
    }

 */

}
