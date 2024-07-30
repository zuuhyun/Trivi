package web.trivi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.trivi.domain.AccompanyBoard;
import web.trivi.domain.BoardImage;
import web.trivi.domain.BoardType;
import web.trivi.domain.User;
import web.trivi.dto.*;
import web.trivi.service.AccBoardService;
import web.trivi.service.ImgPathSaveService;
import web.trivi.service.UserService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class AccBoardApiController {

    private final AccBoardService accBoardService;
    private final ImgPathSaveService imgPathSaveService;
    private final UserService userService;

    @PostMapping("/api/accompany")
    public ResponseEntity<AccompanyBoard> addAccompany(@RequestBody AddAccBoardRequest request) {
        AccompanyBoard savedAcc = accBoardService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedAcc);
    }

    //게시판의 이메일을 기준으로 User 테이블의 프로필 사진 조회 함수
    private AccBoardResponse toAccBoardResponse(AccompanyBoard accompanyBoard) {
        Optional<User> userOptional = userService.findByEmail(accompanyBoard.getAuthor());
        User user = userOptional.orElseGet(() -> {
            User defaultUser = User.createDefaultUser();
            defaultUser.setImgPath(null);
            return defaultUser;
        });
        return new AccBoardResponse(accompanyBoard, user);
    }

    @GetMapping("/api/accompany")
    public ResponseEntity<List<AccBoardResponse>> findAllAccompany() {
        List<AccBoardResponse> accompany = accBoardService.findAll()
                .stream()
                .map(this::toAccBoardResponse)
                .toList();

        return ResponseEntity.ok()
                .body(accompany);
    }

    @GetMapping("/api/accompany/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") long id) {
        AccompanyBoard accompanyBoard = accBoardService.findById(id);
        return ResponseEntity.ok().body(toAccBoardResponse(accompanyBoard));
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
                .map(this::toAccBoardResponse)
                .toList();

        return ResponseEntity.ok()
                .body(accompany);
    }

    @GetMapping("/api/accompany/date")
    public ResponseEntity<List<AccBoardResponse>> getAccompanyByCityAndAfterMeetingTime(
        @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate meetingDate){
        List<AccBoardResponse> accompany = accBoardService.getByMeetingDateGreaterThanEqual(meetingDate)
                .stream()
                .map(this::toAccBoardResponse)
                .toList();
        return ResponseEntity.ok(accompany);
    }

    @GetMapping("/api/accompany/top3")
    public ResponseEntity<List<AccBoardResponse>> getAccompanyByAfterTodayOrderByViewCount(){
        List<AccBoardResponse> accompany = accBoardService.getByAfterTodayOrderByViewCount()
                .stream()
                .map(this::toAccBoardResponse)
                .limit(3)  // 상위 3개의 결과만 추출
                .collect(Collectors.toList());
        return ResponseEntity.ok(accompany);
    }

    @GetMapping("/api/accompany/search")
    public ResponseEntity<List<AccBoardResponse>> getAccompanyByCityAndAfterMeetingTime(
            @RequestParam("city") String city,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate meetingDate){
            List<AccBoardResponse> accompany = accBoardService.getByCityAndMeetingDateGreaterThanEqual(city, meetingDate)
                    .stream()
                    .map(this::toAccBoardResponse)
                    .toList();
        return ResponseEntity.ok(accompany);
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
