package web.trivi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.trivi.domain.AccJoinStatus;
import web.trivi.domain.AccompanyJoin;
import web.trivi.dto.AddAccJoinRequest;
import web.trivi.dto.UpdateAccJoinRequest;
import web.trivi.service.AccJoinService;

@RequiredArgsConstructor
@RestController
public class AccJoinApiController {

    private final AccJoinService accJoinService;

    //참여신청
    @PostMapping("/api/acc-join")
    public ResponseEntity<AccompanyJoin> addAccJoin(@RequestBody AddAccJoinRequest request) {
        AccompanyJoin savedAccJoin = accJoinService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedAccJoin);
    }

    //취소,승인,거절 상태변경
    @PutMapping("/api/acc-join/{id}/status")
    public ResponseEntity<AccompanyJoin> updateAccJoinStatus(@PathVariable("id") long id, @RequestParam("status") String statusStr) {
        AccJoinStatus status;
        switch (statusStr) {
            case "CANCELLED":
                status = AccJoinStatus.CANCELLED;
                break;
            case "CONFIRMED":
                status = AccJoinStatus.CONFIRMED;
                break;
            case "REJECTED":
                status = AccJoinStatus.REJECTED;
                break;
            default:
                return ResponseEntity.badRequest().body(null);  // 상태 값이 유효하지 않을 경우
        }

        AccompanyJoin updatedAccJoin = accJoinService.update(id, status);
        return ResponseEntity.ok().body(updatedAccJoin);
    }

}
