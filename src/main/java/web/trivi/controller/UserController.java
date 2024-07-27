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

    @GetMapping("/login")
    public boolean login(){
        return true;
    }

    // /api/v1/users/login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserLoginDto loginRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        User user = userService.login(loginRequest);

        if(user==null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 기존의 세션 파기하고 새로운 세션 생성
        httpServletRequest.getSession().invalidate();
        HttpSession session = httpServletRequest.getSession(true);

        //세선 30분동안 유지
        session.setMaxInactiveInterval(1800);

        // 응답에 세션 ID 포함
        Map<String, Object> response = new HashMap<>();
        response.put("sessionId", session.getId());

        //세션 생성 테스트
//        sessionList.put(session, session);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/logout")
    public boolean logout(HttpServletRequest request) {

        sessionList.remove(request.getSession());

        // Session이 없으면 return null
        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        return true;
    }


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

}
