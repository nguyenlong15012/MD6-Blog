package com.example.projectmd6.repository;

import com.example.projectmd6.model.Comment;
import com.example.projectmd6.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    Iterable<Comment> findAllByPost (Post post);
}
