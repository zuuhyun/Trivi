package web.trivi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.trivi.domain.AccompanyJoin;
import web.trivi.dto.AddAccJoinRequest;
import web.trivi.repository.AccJoinRepository;

@RequiredArgsConstructor
@Service
public class AccJoinService {
    private final AccJoinRepository accJoinRepository;

    public AccompanyJoin save(AddAccJoinRequest request){
        return accJoinRepository.save(request.toEntity());
    }
}
