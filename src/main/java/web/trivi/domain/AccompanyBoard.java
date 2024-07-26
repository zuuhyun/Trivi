package web.trivi.domain;

import jakarta.persistence.*;
import lombok.*;
//import org.geolatte.geom.Point;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
public class AccompanyBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO FK
    @Column(nullable = false)
    private Long userId;

    //TODO FK
    @Column(nullable = false)
    private Long boardId;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime meetingTime;

    @Column(nullable = false)
    private int totalPeople;

    @Column(nullable = false)
    private int currentPeople;

    @Column(length = 10, nullable = false)
    private String status;

    @Column(nullable = false)
    private Long totalView;

    @Column(nullable = false)
    private Long totalLike;

    @Column(length=100, nullable = false)
    private String locationName;

    @Column(length=100, nullable = false)
    private String nation;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}
