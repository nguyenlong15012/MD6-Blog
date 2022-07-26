package com.example.projectmd6.service;

import com.example.projectmd6.model.Like;

public interface ILikeService {
    Integer  countByPostIdPost(Long idPost);

    void deleteLikePost(Like likePost);

    Like save(Like likePost);

    Like findByPostIdPostAndIdUser(Long idPost, Long idUser);
}
