package web.trivi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.trivi.domain.User;
import web.trivi.dto.UserJoinDto;
import web.trivi.dto.UserLoginDto;
import web.trivi.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    
    public boolean checkEmailExists(String email){
        return userRepository.existsByEmail(email);
    }

    public boolean checkNicknameExists(String nickname){
        return userRepository.existsByNickname(nickname);
    }

    public User join(UserJoinDto joinRequest){
        return userRepository.save(joinRequest.toEntity());
    }

    public User joinPwEncoded(UserJoinDto joinRequest){
        return userRepository.save(joinRequest.toEntity(passwordEncoder.encode(joinRequest.getPassword())));
    }
    
    public User login(UserLoginDto loginRequest) {

        Optional<User> OptionalUser = userRepository.findByEmail(loginRequest.getEmail());

        //존재하지 않는 이메일
        if (OptionalUser.isEmpty()) {
            return null;
        }

        User user = OptionalUser.get();

        //비밀번호 불일치
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            return null;
        }

        return user;
    }

    public User getLoginUserByEmail(String email){
        if(email == null) {
            return null;
        }

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isEmpty()) {
            return null;
        }

        return optionalUser.get();
    }

}
