package web.trivi.controller;

import org.springframework.web.bind.annotation.*;
import web.trivi.dto.ApiResponse;
import web.trivi.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class AmazonS3Controller {

    private final AwsS3Service awsS3Service;

    @PostMapping("/image")
    public ResponseEntity<?> s3Upload(@RequestPart(value = "image", required = false) MultipartFile image){
        String profileImage = awsS3Service.upload(image);
        return ResponseEntity.ok(profileImage);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> s3delete(@RequestParam String addr){
        awsS3Service.deleteImageFromS3(addr);
        return ResponseEntity.ok(null);
    }

    /*
    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestPart("file") MultipartFile multipartFile) {
        return ApiResponse.success(awsS3Service.uploadImage(multipartFile));
    }


    @DeleteMapping("/image")
    public ResponseEntity<Void> deleteImage(@RequestParam("fileName") String fileName) {
        awsS3Service.deleteImage(fileName);
        return ApiResponse.success(null);
    }
    */
}