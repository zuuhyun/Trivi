package web.trivi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.trivi.domain.AccJoinStatus;
import web.trivi.domain.AccompanyJoin;
import web.trivi.dto.AddAccJoinRequest;
import web.trivi.dto.UpdateAccJoinRequest;
import web.trivi.repository.AccJoinRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AccJoinService {
    private final AccJoinRepository accJoinRepository;

    public AccompanyJoin save(AddAccJoinRequest request){
        return accJoinRepository.save(request.toEntity());
    }

    @Transactional
    public AccompanyJoin update(Long id, AccJoinStatus status){
        AccompanyJoin accompanyJoin = accJoinRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found: " + id));
        accompanyJoin.update(status, LocalDateTime.now());
        return accompanyJoin;
    }
}
