package web.trivi.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
public class BoardImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    @Column(nullable = false)
    private BoardType boardType;

    @Column(nullable = false)
    private Long boardId;

    private String imgPath;

    @Builder
    public BoardImage(BoardType boardType, Long boardId, String imgPath) {
        this.boardType = boardType;
        this.boardId = boardId;
        this.imgPath = imgPath;
    }
}
