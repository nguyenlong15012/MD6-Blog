package com.example.projectmd6.controller;

import com.example.projectmd6.model.Post;
import com.example.projectmd6.model.Users;
import com.example.projectmd6.service.IPostService;
import com.example.projectmd6.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

    @GetMapping("/view/{idUser}")
    public ResponseEntity<Iterable<Post>> findAllPostByUser(@PathVariable Long idUser){
        Optional<Users> usersOptional = userService.findById(idUser);
        Iterable<Post> posts = postService.findAllByUser(usersOptional.get());
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
