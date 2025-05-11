package com.backend.meltique.post.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostCreateDto {
    private String title;
    private String content;
    private List<ImageDto> imageUrls = new ArrayList<>();
    private String shopName;
    private String shopLink;
    private int price;

}
