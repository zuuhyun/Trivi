package web.trivi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.trivi.domain.AccompanyBoard;
import web.trivi.domain.BoardImage;
import web.trivi.domain.BoardType;
import web.trivi.dto.*;
import web.trivi.service.AccBoardService;
import web.trivi.service.ImgPathSaveService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AccBoardApiController {

    private final AccBoardService accBoardService;
    private final ImgPathSaveService imgPathSaveService;

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

    @GetMapping("/api/accompany/{id}")
    public ResponseEntity<AccBoardResponse> findById(@PathVariable("id") long id) {
        AccompanyBoard accompanyBoard = accBoardService.findById(id);
        return ResponseEntity.ok()
                .body(new AccBoardResponse(accompanyBoard));
    }

    @GetMapping("/api/accompany/img-path")
    public ResponseEntity<List<ImgPathResponse>> findAllImgPath() {
        List<ImgPathResponse> imgPath = imgPathSaveService.findAll()
                .stream()
                .map(ImgPathResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(imgPath);
    }

    @GetMapping("/api/accompany/img-path/{board-id}")
    public ResponseEntity<List<ImgPathResponse>> findAllByBoardIdAndBoardType(@PathVariable("board-id") Long boardId) {
        List<ImgPathResponse> imgPath = imgPathSaveService.findAllByBoardIdAndBoardType(boardId, BoardType.ACC)
                .stream()
                .map(ImgPathResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(imgPath);
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
    public ResponseEntity<Void> deleteAccompany(@PathVariable("id") long id) {
        accBoardService.delete(id);
        imgPathSaveService.delete(id,BoardType.ACC);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/accompany/{id}")
    public ResponseEntity<AccompanyBoard> updateAccompany(@PathVariable("id") long id, @RequestBody UpdateAccBoardRequest request) {
        AccompanyBoard updatedAccompany = accBoardService.update(id, request);
        return ResponseEntity.ok()
                .body(updatedAccompany);
    }
}
