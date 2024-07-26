package web.trivi.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
//import org.geolatte.geom.Point;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class AccompanyBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    //TODO FK
    @Column(nullable = false)
    private Long userId;
    */

    @Enumerated
    @Column(nullable = false)
    private BoardType boardType;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime meetingTime;

    @Column(nullable = false)
    private int totalPeople;

    @ColumnDefault("0")
    @Column(nullable = false)
    private int currentPeople;

    @Enumerated
    @Column(nullable = false)
    private AccStatus status;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Long totalView;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Long totalLike;

    @Column(length=100, nullable = false)
    private String locationName;

    @Column(length=100, nullable = false)
    private String nation;

    @Column(length=100, nullable = false)
    private String city;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String author;

    @Builder
    public AccompanyBoard(String title, String author, BoardType boardType, AccStatus accStatus, Long totalLike, Long totalView, String content, LocalDateTime createdAt, int currentPeople, String city, String locationName, LocalDateTime meetingTime, String nation, int totalPeople){
        this.title = title;
        this.author = author;
        this.content = content;
        this.status =  accStatus;
        this.createdAt = createdAt;
        this.boardType = boardType;
        this.currentPeople = currentPeople;
        this.meetingTime = meetingTime;
        this.nation = nation;
        this.locationName = locationName;
        this.totalPeople = totalPeople;
        this.city = city;
        this.totalLike = totalLike;
        this.totalView = totalView;
    }

    public void update(String title, String content, String locationName){
        this.title = title;
        this.content = content;
        this.locationName = locationName;
    }
}
