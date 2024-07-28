package web.trivi.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.trivi.domain.User;
import web.trivi.dto.UserDto;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Optional<User> findByEmail(@Param("email") String email);

    @Modifying @Transactional @Query("UPDATE User u SET u.triptype = :triptype WHERE u.email = :email")
    int updateTriptypeByEmail(String triptype, String email);

    @Query("SELECT new web.trivi.dto.UserDto(u.email, u.name, u.gender, u.birth, u.phone, u.triptype, u.nickname, u.verificationYn, u.createdAt, u.imgPath) " +
            "FROM User u WHERE u.email = :email")
    Optional<UserDto> findWithoutPasswordByEmail(@Param("email") String email);
}
