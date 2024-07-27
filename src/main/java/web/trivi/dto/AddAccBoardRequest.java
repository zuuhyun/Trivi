package web.trivi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.trivi.domain.AccStatus;
import web.trivi.domain.AccompanyBoard;
import web.trivi.domain.BoardType;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddAccBoardRequest {
    private String title;
    private String content;
    private int totalPeople;
    private String city;
    private String nation;
    private String locationName;
    private LocalDateTime meetingTime;
    private Long totalView;
    private Long totalLike;
    private String author;
    private BoardType boardType;
    private AccStatus status;
    private String userNickname;
    private String imgPath;

    public AccompanyBoard toEntity() {
        return AccompanyBoard.builder()
                .title(title)
                .author(author)
                .userNickname(userNickname)
                .boardType(BoardType.ACC)
                .content(content)
                .totalPeople(totalPeople)
                .nation(nation)
                .city(city)
                .status(AccStatus.PROGRESS)
                .createdAt(LocalDateTime.now())
                .locationName(locationName)
                .meetingTime(meetingTime)
                .boardImgPath(imgPath)
                .totalLike(0L)
                .totalView(0L)
                .build();
    }
}
