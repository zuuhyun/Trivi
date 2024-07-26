package web.trivi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.trivi.domain.BoardImage;
import web.trivi.domain.BoardType;
import web.trivi.dto.AddImgPathRequest;
import web.trivi.repository.BoardImgRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ImgPathSaveService {
    private final BoardImgRepository boardImgRepository;

    public BoardImage save(AddImgPathRequest request){
        return boardImgRepository.save(request.toEntity());
    }

    public List<BoardImage> findAll(){
        return boardImgRepository.findAll();
    }

    public List<BoardImage> findAllByBoardIdAndBoardType(Long boardId, BoardType boardType) {
        return boardImgRepository.findByBoardIdAndBoardType(boardId, boardType);
    }

    @Transactional
    public void delete(Long boardId, BoardType boardType){
        boardImgRepository.deleteByBoardIdAndBoardType(boardId, boardType);
    }

    @Transactional
    public void update(Long boardId, BoardType boardType, String imgPath){
        List<BoardImage> boardImages = boardImgRepository.findByBoardIdAndBoardType(boardId, boardType);
        // 리스트가 비어 있으면 새 객체를 저장하고 반환합니다
        if (boardImages.isEmpty()) {
            boardImgRepository.save(BoardImage.builder()
                    .boardId(boardId)
                    .boardType(boardType)
                    .imgPath(imgPath)
                    .build());
        } else {
            // 리스트에 항목이 있으면 첫 번째 항목업데이트
            boardImages.get(0).update(imgPath);
        }
    }
}

