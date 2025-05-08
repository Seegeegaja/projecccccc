package com.backend.meltique.post.controller;

import com.backend.meltique.config.awsconfig.s3.S3Service;
import com.backend.meltique.post.entity.Post;
import com.backend.meltique.post.repository.PostRepository;
import com.backend.meltique.post.service.PostService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostRepository postRepository;
    private final S3Service s3Service;

    public PostController(PostService postService, PostRepository postRepository, S3Service s3Service) {
        this.postRepository = postRepository;
        this.s3Service = s3Service;
    }

    @PostMapping("/create")
    public ResponseEntity<Post> insertPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(value = "file" , required = false)MultipartFile file
            ){
        String imageUrl = null;
        if (file != null && !file.isEmpty()) {
            imageUrl = s3Service.uploadFile(file);
        }

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setImageUrl(imageUrl);
        post.setLikeCount(0);
        post.setViewCount(0);
        Post savePost = postRepository.save(post);
        return ResponseEntity.ok(savePost);

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

