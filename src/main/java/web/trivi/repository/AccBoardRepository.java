package web.trivi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.trivi.domain.AccompanyBoard;

import java.util.List;
import java.util.Optional;

public interface AccBoardRepository extends JpaRepository<AccompanyBoard, Long> {
    List<AccompanyBoard> findByCity(String city);
}
