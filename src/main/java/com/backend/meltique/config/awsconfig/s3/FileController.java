package com.backend.meltique.config.awsconfig.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public FileController(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }


    @PostMapping("/file/stream")
    public void streamUpload(HttpServletRequest request) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(request.getContentType());
        metadata.setContentLength(request.getContentLengthLong());

        String objectKey = "uploads/" + System.currentTimeMillis();
        amazonS3Client.putObject(bucketName, objectKey, request.getInputStream(), metadata);
        //    지금 올라간 이미지의 EndPoint 값을 읽어와서
        //    DB에 저장
        String fileUrl = amazonS3Client.getUrl(bucketName, objectKey).toString();
        System.out.println(fileUrl);

    }

}

