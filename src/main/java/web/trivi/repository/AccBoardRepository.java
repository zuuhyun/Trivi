package web.trivi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.trivi.domain.AccompanyBoard;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AccBoardRepository extends JpaRepository<AccompanyBoard, Long> {
    List<AccompanyBoard> findByCity(String city);
    List<AccompanyBoard> findByCityAndMeetingTimeAfter(String city, LocalDateTime meetingTime);
    List<AccompanyBoard> findByMeetingTimeAfterOrderByMeetingTimeAsc(LocalDateTime meetingTime);
    //오늘날짜기준으로높은조회수
    List<AccompanyBoard> findByMeetingTimeAfterOrderByTotalViewDesc(LocalDateTime meetingTime);

}
