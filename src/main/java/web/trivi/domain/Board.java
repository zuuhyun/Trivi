package web.trivi.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Board {

    @Id
    @Column(length = 100)
    @Enumerated(EnumType.STRING)
    private BoardType id;

    @Column(length = 100, nullable = false)
    private String board_name;

}
