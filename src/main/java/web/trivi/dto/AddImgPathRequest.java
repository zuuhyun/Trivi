package web.trivi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.trivi.domain.BoardImage;
import web.trivi.domain.BoardType;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddImgPathRequest {
    private BoardType boardType;
    private Long boardId;
    private String imgPath;

    public BoardImage toEntity(){
        return BoardImage.builder()
                .boardType(boardType)
                .boardId(boardId)
                .imgPath(imgPath)
                .build();
    }
}
