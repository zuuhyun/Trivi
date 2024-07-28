package web.trivi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.trivi.domain.AccompanyBoard;
import web.trivi.domain.BoardImage;
import web.trivi.domain.BoardType;
import web.trivi.domain.User;
import web.trivi.dto.AddAccBoardRequest;
import web.trivi.dto.AddImgPathRequest;
import web.trivi.dto.UpdateAccBoardRequest;
import web.trivi.repository.AccBoardRepository;
import web.trivi.repository.BoardImgRepository;
import web.trivi.repository.UserRepository;
import web.trivi.service.ImgPathSaveService;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccBoardService {
    private final AccBoardRepository accBoardRepository;
    private final ImgPathSaveService imgPathSaveService;
    private final UserRepository userRepository;

    public AccompanyBoard save(AddAccBoardRequest request){
        Optional<User> userOptional = userRepository.findByEmail(request.getAuthor());
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = userOptional.get();
        AccompanyBoard accompanyBoard = request.toEntity();
        accompanyBoard.setUserNickname(user.getNickname());
        accompanyBoard = accBoardRepository.save(accompanyBoard);

        if (request.getImgPath() != null){
            imgPathSaveService.save(new AddImgPathRequest(BoardType.ACC, accompanyBoard.getId(), request.getImgPath()));
        }
        return accompanyBoard;
    }

    public List<AccompanyBoard> findAll(){
        return accBoardRepository.findAll();
    }

    public AccompanyBoard findById(long id){
        return accBoardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found: " + id));
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

        if (request.getImgPath() != null) {
            imgPathSaveService.update(id, BoardType.ACC, request.getImgPath());
        }

        accompany.update(title, content, locationName);



        return accompany;
    }
}
