package web.trivi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.trivi.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);

    Optional<User> findByKakaoId(Long kakaoId);
}
