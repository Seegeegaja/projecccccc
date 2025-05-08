package com.backend.meltique.post.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostDTO {
    private String title;
    private String content;
    private MultipartFile imageUrl;
}
