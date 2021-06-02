package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.repos.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<Post> posts(){
        return postRepository.findAll();
    }
    @GetMapping("/{id}")
    public List<Post> posts(@PathVariable Long id){
        return postRepository.findAllById(id);
    }
}
