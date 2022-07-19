package com.example.projectmd6.repository;

import com.example.projectmd6.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepository extends JpaRepository<Post,Long> {
}
