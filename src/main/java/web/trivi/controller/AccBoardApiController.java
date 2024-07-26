package web.trivi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.trivi.domain.AccompanyBoard;
import web.trivi.dto.AddAccBoardRequest;
import web.trivi.service.AccBoardService;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class AccBoardApiController {

    @Autowired
    private final AccBoardService accBoardService;

    @PostMapping("/api/accompany")
    public ResponseEntity<AccompanyBoard> addAccompany(@RequestBody AddAccBoardRequest request) {
        AccompanyBoard savedAcc = accBoardService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedAcc);
    }
}
