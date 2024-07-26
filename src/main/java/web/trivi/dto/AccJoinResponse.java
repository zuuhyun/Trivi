package web.trivi.dto;

import lombok.Getter;
import web.trivi.domain.AccJoinStatus;
import web.trivi.domain.AccompanyJoin;

import java.time.LocalDateTime;

@Getter
public class AccJoinResponse {
    private Long accBoardId;
    private String applyEmail;
    private AccJoinStatus status;
    private LocalDateTime createdAt;

    public AccJoinResponse(AccompanyJoin accompanyJoin){
        accBoardId = accompanyJoin.getAccBoardId();
        applyEmail = accompanyJoin.getApplyEmail();
        status = accompanyJoin.getStatus();
        createdAt = accompanyJoin.getCreatedAt();
    }
}
