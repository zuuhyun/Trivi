package web.trivi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.trivi.domain.BoardImage;

public interface BoardImgRepository extends JpaRepository<BoardImage, Long> {
}
