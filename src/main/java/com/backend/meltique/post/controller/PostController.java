package com.backend.meltique.post.controller;

import com.backend.meltique.post.entity.Post;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("post")
public class PostController {

    @GetMapping("/add")
    public ResponseEntity<Post> insertPost(){
        return null;

    }
}
