package com.backend.meltique.post.controller;

import com.backend.meltique.config.awsconfig.s3.S3Service;
import com.backend.meltique.post.dto.PostCreateDto;
import com.backend.meltique.post.dto.PostDTO;
import com.backend.meltique.post.entity.Post;
import com.backend.meltique.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final S3Service s3Service;
    private final PostService postService;

    public PostController(PostService postService, S3Service s3Service) {
        this.s3Service = s3Service;
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<PostDTO> insertPost(
            @RequestPart("dto") PostCreateDto dto,
            @RequestPart("file")List<MultipartFile> file
            ){
        PostDTO dtos = postService.createPost(dto, file);
        return ResponseEntity.ok(dtos);
    }
}

//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//        import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/products")
//@RequiredArgsConstructor
//public class ProductController {
//
//    private final ProductRepository productRepository;
//    private final S3Service s3Service;  // S3 업로드 서비스
//
//    @PostMapping
//    public ResponseEntity<?> createProduct(
//            @RequestParam String name,
//            @RequestParam String description,
//            @RequestParam int price,
//            @RequestParam("image") MultipartFile imageFile
//    ) {
//        // 1️⃣ S3에 이미지 업로드
//        String imageUrl = s3Service.uploadFile(imageFile);
//
//        // 2️⃣ Product 객체 생성
//        Product product = new Product();
//        product.setName(name);
//        product.setDescription(description);
//        product.setPrice(price);
//        product.setImageUrl(imageUrl);
//
//        // 3️⃣ 저장
//        productRepository.save(product);
//
//        return ResponseEntity.ok("상품 등록 성공!");
//    }
//}

