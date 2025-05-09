package com.backend.meltique.post.service;

import com.backend.meltique.config.awsconfig.s3.Image;
import com.backend.meltique.config.awsconfig.s3.S3Service;
import com.backend.meltique.post.dto.ImageDto;
import com.backend.meltique.post.dto.PostCreateDto;
import com.backend.meltique.post.dto.PostDTO;
import com.backend.meltique.post.entity.Post;
import com.backend.meltique.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final S3Service s3Service;

    public PostService(PostRepository postRepository, S3Service s3Service) {
        this.postRepository = postRepository;
        this.s3Service = s3Service;
    }
    @Transactional
    public PostDTO createPost(PostCreateDto dto, List<MultipartFile> files) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setViewCount(0);
        post.setLikeCount(0);
        post = postRepository.save(post);

        if (dto.getImageUrls() != null && !dto.getImageUrls().isEmpty()) {
            for (ImageDto url : dto.getImageUrls()) {
                Image image = new Image();
                image.setFileUrl(url.getFileUrl());
                post.addImage(image);
            }
        }
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                String fileUrl = s3Service.uploadFile(file);
                Image image = new Image();
                image.setFileUrl(fileUrl);
                post.addImage(image);
            }
        }
        List<ImageDto> imageDtos = post.getImages().stream()
                .map(img -> new ImageDto(img.getId(), img.getFileUrl()))
                .toList();

        PostDTO responseDto = new PostDTO();
        responseDto.setId(post.getId());
        responseDto.setTitle(post.getTitle());
        responseDto.setContent(post.getContent());
        responseDto.setImageUrls(imageDtos);
        return responseDto;
    }
}
