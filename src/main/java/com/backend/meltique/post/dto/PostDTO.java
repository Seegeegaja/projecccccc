package com.backend.meltique.post.dto;

import com.backend.meltique.post.entity.Post;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private List<ImageDto> imageUrls;


}
