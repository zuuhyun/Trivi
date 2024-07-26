package web.trivi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.trivi.domain.BoardImage;
import web.trivi.domain.BoardType;
import web.trivi.dto.AddImgPathRequest;
import web.trivi.repository.BoardImgRepository;

@RequiredArgsConstructor
@Service
public class ImgPathSaveService {
    private final BoardImgRepository boardImgRepository;

    public BoardImage save(AddImgPathRequest request){
        BoardImage boardImage = request.toEntity();
        return boardImgRepository.save(boardImage);
    }
}

