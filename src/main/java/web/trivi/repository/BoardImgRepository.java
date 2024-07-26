package web.trivi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.trivi.domain.BoardImage;
import web.trivi.domain.BoardType;

import java.util.List;

public interface BoardImgRepository extends JpaRepository<BoardImage, Long> {
    List<BoardImage> findByBoardIdAndBoardType(Long boardId, BoardType boardType);
}
