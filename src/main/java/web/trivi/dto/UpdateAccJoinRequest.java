package web.trivi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import web.trivi.domain.AccJoinStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateAccJoinRequest {
    private AccJoinStatus status;
    private LocalDateTime localDateTime;
}
