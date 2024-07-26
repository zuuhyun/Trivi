package web.trivi.dto;

import lombok.Getter;
import web.trivi.domain.AccStatus;
import web.trivi.domain.AccompanyBoard;
import web.trivi.domain.BoardType;

import java.time.LocalDateTime;

@Getter
public class AccBoardResponse {
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
    private LocalDateTime createdAt;

    public AccBoardResponse(AccompanyBoard accompanyBoard) {
        this.title = accompanyBoard.getTitle();
        this.content = accompanyBoard.getContent();
        this.totalPeople = accompanyBoard.getTotalPeople();
        this.city = accompanyBoard.getCity();
        this.nation = accompanyBoard.getNation();
        this.locationName = accompanyBoard.getLocationName();
        this.meetingTime = accompanyBoard.getMeetingTime();
        this.status = accompanyBoard.getStatus();
        this.totalLike = accompanyBoard.getTotalLike();
        this.totalView = accompanyBoard.getTotalView();
        this.author = accompanyBoard.getAuthor();
        this.boardType = accompanyBoard.getBoardType();
        this.createdAt = accompanyBoard.getCreatedAt();
    }

}
