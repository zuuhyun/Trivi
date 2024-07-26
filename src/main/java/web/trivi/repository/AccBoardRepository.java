package web.trivi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.trivi.domain.AccompanyBoard;

public interface AccBoardRepository extends JpaRepository<AccompanyBoard, Long> {
}
