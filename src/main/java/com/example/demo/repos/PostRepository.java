package com.example.demo.repos;

import com.example.demo.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAll();
    List<Post> findAllById(Long id);
}
