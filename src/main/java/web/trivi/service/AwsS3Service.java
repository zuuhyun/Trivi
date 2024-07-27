//package web.trivi.service;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.CannedAccessControlList;
//import com.amazonaws.services.s3.model.DeleteObjectRequest;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.amazonaws.util.IOUtils;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.server.ResponseStatusException;
//import web.trivi.config.aws.ErrorCode;
//import web.trivi.config.aws.S3Exception;
//
//import java.io.*;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLDecoder;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class AwsS3Service {
//
//    private final AmazonS3 amazonS3;
//
//    @Value("${cloud.aws.s3.bucket-name}")
//    private String bucketName;
//
//    public String upload(MultipartFile image) {
//        if(image.isEmpty() || Objects.isNull(image.getOriginalFilename())){
//            throw new S3Exception(ErrorCode.EMPTY_FILE_EXCEPTION);
//        }
//        return this.uploadImage(image);
//    }
//
//    private String uploadImage(MultipartFile image) {
//        this.validateImageFileExtention(image.getOriginalFilename());
//        try {
//            return this.uploadImageToS3(image);
//        } catch (IOException e) {
//            throw new S3Exception(ErrorCode.IO_EXCEPTION_ON_IMAGE_UPLOAD);
//        }
//    }
//
//    private void validateImageFileExtention(String filename) {
//        int lastDotIndex = filename.lastIndexOf(".");
//        if (lastDotIndex == -1) {
//            throw new S3Exception(ErrorCode.NO_FILE_EXTENTION);
//        }
//
//        String extention = filename.substring(lastDotIndex + 1).toLowerCase();
//        List<String> allowedExtentionList = Arrays.asList("jpg", "jpeg", "png", "gif");
//
//        if (!allowedExtentionList.contains(extention)) {
//            throw new S3Exception(ErrorCode.INVALID_FILE_EXTENTION);
//        }
//    }
//
//    private String uploadImageToS3(MultipartFile image) throws IOException {
//        String originalFilename = image.getOriginalFilename(); //원본 파일 명
//        String extention = originalFilename.substring(originalFilename.lastIndexOf(".")); //확장자 명
//
//        String s3FileName = UUID.randomUUID().toString().substring(0, 10) + originalFilename; //변경된 파일 명
//
//        InputStream is = image.getInputStream();
//        byte[] bytes = IOUtils.toByteArray(is);
//
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentType("image/" + extention);
//        metadata.setContentLength(bytes.length);
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
//
//        try{
//            PutObjectRequest putObjectRequest =
//                    new PutObjectRequest(bucketName, s3FileName, byteArrayInputStream, metadata)
//                            .withCannedAcl(CannedAccessControlList.PublicRead);
//            amazonS3.putObject(putObjectRequest); // put image to S3
//        }catch (Exception e){
//            throw new S3Exception(ErrorCode.PUT_OBJECT_EXCEPTION);
//        }finally {
//            byteArrayInputStream.close();
//            is.close();
//        }
//
//        return amazonS3.getUrl(bucketName, s3FileName).toString();
//    }
//
//    public void deleteImageFromS3(String imageAddress){
//        String key = getKeyFromImageAddress(imageAddress);
//        try{
//            amazonS3.deleteObject(new DeleteObjectRequest(bucketName, key));
//        }catch (Exception e){
//            throw new S3Exception(ErrorCode.IO_EXCEPTION_ON_IMAGE_DELETE);
//        }
//    }
//
//    private String getKeyFromImageAddress(String imageAddress) {
//        try {
//            URL url = new URL(imageAddress);
//            String decodingKey = URLDecoder.decode(url.getPath(), "UTF-8");
//            return decodingKey.substring(1); // 맨 앞의 '/' 제거
//
//        } catch (MalformedURLException | UnsupportedEncodingException e) {
//            throw new S3Exception(ErrorCode.IO_EXCEPTION_ON_IMAGE_DELETE);
//        }
//    }
///*
//    private final AmazonS3 amazonS3;
//    @Value("${cloud.aws.s3.bucket-name}")
//    private String bucket;
//
//    public String uploadImage(MultipartFile file) {
//        File convertedFile = convertMultiPartFileToFile(file);
//        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//        amazonS3.putObject(bucket, fileName, convertedFile);
//        convertedFile.delete();
//        return amazonS3.getUrl(bucket, fileName).toString();
//    }
//
//    public void deleteImage(String fileName) {
//        amazonS3.deleteObject(bucket, fileName);
//    }
//
//    private File convertMultiPartFileToFile(MultipartFile file) {
//        File convertedFile = new File(file.getOriginalFilename());
//        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
//            fos.write(file.getBytes());
//        } catch (IOException e) {
//            throw new RuntimeException("Error converting multipart file to file", e);
//        }
//        return convertedFile;
//    }
//
//    private String createFileName(String fileName) {
//        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
//    }
//
//    private String getFileExtension(String fileName) {
//        try {
//            return fileName.substring(fileName.lastIndexOf("."));
//        } catch (StringIndexOutOfBoundsException e) {
//            throw new IllegalArgumentException(String.format("잘못된 형식의 파일 (%s) 입니다.", fileName));
//        }
//    }
//
// */
//}
