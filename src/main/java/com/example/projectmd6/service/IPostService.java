package com.example.projectmd6.service;

import com.example.projectmd6.model.Post;
import com.example.projectmd6.model.Users;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    Iterable<Post> findAll();

    Optional<Post> findById(Long id);

    void save(Post post);

    void remove(Long id);

    Iterable<Post> findAllByUser(Users users);

    List<Post> findPostsByTitleContaining(String title);

//    List<Post> findByTag_Name(String tagName);

    //danh sach cong ty khong khoa
    Iterable<Post> findAllUnlockPost();

    //danh sach cong ty bi khoa
    Iterable<Post> findAllLockPost();
}
