package com.example.projectmd6.service;

import com.example.projectmd6.model.Comment;
import com.example.projectmd6.model.Post;

import java.util.Optional;

public interface ICommentService {
    Iterable<Comment> findAll();

    Optional<Comment> findById(Long id);

    void save(Comment comment);

    void remove(Long id);

    Iterable<Comment> findAllByPost (Post post);
}
