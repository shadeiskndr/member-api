package com.shahathir.membership_crud3;

import io.minio.MinioClient;

import io.minio.PutObjectArgs;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.IOUtils;

@Service
public class ImageService {
    @Autowired
    private MinioClient minioClient;

    private static final String BUCKET_NAME = "member";

    public String uploadImage(MultipartFile file, String memberId) {
        try {
            String fileName = memberId + "-" + file.getOriginalFilename();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(BUCKET_NAME)
                            .object(fileName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
            return fileName;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }

    public byte[] getImage(String fileName) {
        try {
            GetObjectResponse response = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(BUCKET_NAME)
                            .object(fileName)
                            .build());
            return IOUtils.toByteArray(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get image", e);
        }
    }
}
