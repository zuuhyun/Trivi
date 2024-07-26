package web.trivi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.trivi.domain.AccompanyBoard;
import web.trivi.dto.AccBoardResponse;
import web.trivi.dto.AddAccBoardRequest;
import web.trivi.service.AccBoardService;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("/api/accompany")
    public ResponseEntity<List<AccBoardResponse>> findAllAccompany() {
        List<AccBoardResponse> accompany = accBoardService.findAll()
                .stream()
                .map(AccBoardResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(accompany);
    }

    @GetMapping("/api/accompany/city/{city}")
    public ResponseEntity<List<AccBoardResponse>> findAllByCity(@PathVariable("city") String city) {
        List<AccBoardResponse> accompany = accBoardService.findByCity(city)
                .stream()
                .map(AccBoardResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(accompany);
    }

    @DeleteMapping("/api/accompany/{id}")
    public ResponseEntity<Void> deleteAccompany(@PathVariable("id") Long id) {
        accBoardService.delete(id);

        return ResponseEntity.ok()
                .build();
    }
}
