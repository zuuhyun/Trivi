package web.trivi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.trivi.domain.AccJoinStatus;
import web.trivi.domain.AccompanyJoin;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddAccJoinRequest {
    private Long accBoardId;
    private String applyEmail;

    public AccompanyJoin toEntity(){
        return AccompanyJoin.builder()
                .accBoardId(accBoardId)
                .applyEmail(applyEmail)
                .status(AccJoinStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
