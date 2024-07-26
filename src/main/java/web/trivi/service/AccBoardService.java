package web.trivi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.trivi.domain.AccompanyBoard;
import web.trivi.dto.AddAccBoardRequest;
import web.trivi.repository.AccBoardRepository;

@RequiredArgsConstructor
@Service
public class AccBoardService {
    private final AccBoardRepository accBoardRepository;

    public AccompanyBoard save(AddAccBoardRequest request){
        return accBoardRepository.save(request.toEntity());
    }
}
