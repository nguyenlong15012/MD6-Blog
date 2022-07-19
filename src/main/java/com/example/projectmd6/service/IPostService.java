package com.example.projectmd6.service;

import com.example.projectmd6.model.Post;

import java.util.Optional;

public interface IPostService {
    Iterable<Post> findAll();

    Optional<Post> findById(Long id);

    void save(Post post);

    void remove(Long id);
}
