package com.example.projectmd6.service.impl;

import com.example.projectmd6.model.Post;
import com.example.projectmd6.model.Users;
import com.example.projectmd6.repository.IPostRepository;
import com.example.projectmd6.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    private IPostRepository postRepository;

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Page<Post> findAllByUser(Users users, Pageable pageable) {
        return postRepository.findAllByUser(users, pageable);
    }

    @Override
    public List<Post> findPostsByTitleContaining(String title) {
        return postRepository.findPostsByTitleContaining(title);
    }

//    @Override
//    public List<Post> findByTag_Name(String tagName) {
//        return postRepository.findByTag_Name(tagName);
//    }

    @Override
    public Iterable<Post> findAllByStatusPublic() {
        return postRepository.findAllByStatusPublic();
    }

    @Override
    public Page<Post> findAllOrderById(Pageable pageable) {
        return postRepository.findAllOrderById(pageable);
    }


    @Override
    public Iterable<Post> findAllByTitleContaining(String title) {
        return postRepository.findAllByTitleContaining(title);
    }
}
