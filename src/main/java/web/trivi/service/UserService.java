package web.trivi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.trivi.domain.User;
import web.trivi.dto.UserDto;
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

    public UserDto getUserByEmail(String email){
        if(email == null) {
            return null;
        }

        Optional<UserDto> optionalUser = userRepository.findWithoutPasswordByEmail(email);

        if(optionalUser.isEmpty()) {
            return null;
        }

        UserDto user = optionalUser.get();

        return user;
    }

    public boolean modifyTriptype(String triptype, String email){
        int result = userRepository.updateTriptypeByEmail(triptype, email);

        return result == 1 ? true : false;
    }

    public boolean modifyAuth(boolean auth, String email){

        int result = userRepository.updateAuthByEmail(auth, email);

        return result == 1 ? true : false;

    }

}
