package com.example.projectmd6.service.impl;

import com.example.projectmd6.model.Like;
import com.example.projectmd6.repository.ILikeRepository;
import com.example.projectmd6.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements ILikeService {
    @Autowired
    private ILikeRepository likeRepository;

    @Override
    public Integer countByPostIdPost(Long idPost) {
        return likeRepository.countByPostIdPost(idPost);
    }

    @Override
    public void deleteLikePost(Like likePost) {
        likeRepository.delete(likePost);
    }

    @Override
    public Like save(Like likePost) {
        return likeRepository.save(likePost);
    }

    @Override
    public Like findByPostIdPostAndIdUser(Long idPost, Long idUser) {
        return likeRepository.findByPostIdPostAndUserId(idPost, idUser);
    }
}
