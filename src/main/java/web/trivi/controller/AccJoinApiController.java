package web.trivi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.trivi.domain.AccompanyJoin;
import web.trivi.dto.AddAccJoinRequest;
import web.trivi.service.AccJoinService;

@RequiredArgsConstructor
@RestController
public class AccJoinApiController {

    private final AccJoinService accJoinService;

    @PostMapping("/api/acc-join")
    public ResponseEntity<AccompanyJoin> addAccJoin(@RequestBody AddAccJoinRequest request) {
        AccompanyJoin savedAccJoin = accJoinService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedAccJoin);
    }
}
