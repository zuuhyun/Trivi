package web.trivi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.trivi.domain.BoardImage;
import web.trivi.domain.BoardType;
import web.trivi.dto.AddImgPathRequest;
import web.trivi.repository.BoardImgRepository;

import java.util.List;

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
}

