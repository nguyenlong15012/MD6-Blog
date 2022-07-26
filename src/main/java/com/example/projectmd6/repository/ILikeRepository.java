package com.example.projectmd6.repository;

import com.example.projectmd6.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikeRepository extends JpaRepository<Like, Long> {
    Integer countByPostIdPost(Long idPost);

    Like findByPostIdPostAndUserId(Long idPost, Long idUser);
}
