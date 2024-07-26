package web.trivi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateAccBoardRequest {
    private String title;
    private String content;
    private String locationName;
    private String imgPath;
}
