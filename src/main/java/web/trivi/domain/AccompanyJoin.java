package web.trivi.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
public class AccompanyJoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long accBoardId;

    @Column(nullable = false)
    private String applyEmail;

    @Enumerated
    @Column(nullable = false)
    private AccJoinStatus status;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public AccompanyJoin(Long accBoardId, String applyEmail, AccJoinStatus status, LocalDateTime createdAt){
        this.accBoardId = accBoardId;
        this.applyEmail = applyEmail;
        this.status = status;
        this.createdAt = createdAt;
    }

    public void update(AccJoinStatus status, LocalDateTime updatedAt){
        this.status = status;
        this.updatedAt = updatedAt;
    }
}
