package web.trivi.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.trivi.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    @Modifying @Transactional @Query("UPDATE User u SET u.triptype = :triptype WHERE u.email = :email")
    int updateTriptypeByEmail(String triptype, String email);
    Optional<User> findByEmail(String email);
}
