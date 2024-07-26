package web.trivi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.trivi.domain.AccompanyBoard;
import web.trivi.dto.AddAccBoardRequest;
import web.trivi.dto.UpdateAccBoardRequest;
import web.trivi.repository.AccBoardRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccBoardService {
    private final AccBoardRepository accBoardRepository;

    public AccompanyBoard save(AddAccBoardRequest request){
        return accBoardRepository.save(request.toEntity());
    }

    public List<AccompanyBoard> findAll(){
        return accBoardRepository.findAll();
    }

    public List<AccompanyBoard> findByCity(String city){
        return accBoardRepository.findByCity(city);
    }

    public void delete(long id){
        accBoardRepository.deleteById(id);
    }

    @Transactional
    public AccompanyBoard update(long id, UpdateAccBoardRequest request){
        AccompanyBoard accompany = accBoardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found: " + id));

        String title = request.getTitle();
        String content = request.getContent();
        String locationName = request.getLocationName();

        if (request.getTitle() == null) {
            title = accompany.getTitle();
        }

        if (request.getContent() == null) {
            content = accompany.getContent();
        }

        if (request.getLocationName() == null) {
            locationName = accompany.getLocationName();
        }

        accompany.update(title, content, locationName);

        return accompany;
    }
}
