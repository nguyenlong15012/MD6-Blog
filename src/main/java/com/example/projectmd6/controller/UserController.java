package com.example.projectmd6.controller;

import com.example.projectmd6.model.Post;
import com.example.projectmd6.model.Users;
import com.example.projectmd6.service.IPostService;
import com.example.projectmd6.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

//    @GetMapping("/view/{idUser}")
//    public ResponseEntity<Iterable<Post>> findAllPostByUser(@PathVariable Long idUser){
//        Optional<Users> usersOptional = userService.findById(idUser);
//        Iterable<Post> posts = postService.findAllByUser(usersOptional.get());
//        return new ResponseEntity<>(posts, HttpStatus.OK);
//    }

    @GetMapping("/view/{idUser}")
    public ResponseEntity<Page<Post>> findAllPostByUser(@PathVariable Long idUser, @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable){
        Optional<Users> usersOptional = userService.findById(idUser);
        Page<Post> posts = postService.findAllByUser(usersOptional.get(), pageable);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Users>> findAll(@PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Users> users = (Page<Users>) userService.findAll(pageable);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
