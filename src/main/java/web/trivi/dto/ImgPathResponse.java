package web.trivi.dto;

import lombok.Getter;
import web.trivi.domain.BoardImage;
import web.trivi.domain.BoardType;

@Getter
public class ImgPathResponse {
    private BoardType boardType;
    private Long boardId;
    private String imgPath;

    public ImgPathResponse(BoardImage boardImage) {
        this.boardType = boardImage.getBoardType();
        this.boardId = boardImage.getBoardId();
        this.imgPath = boardImage.getImgPath();
    }
}
