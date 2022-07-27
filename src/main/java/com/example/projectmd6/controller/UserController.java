package com.example.projectmd6.controller;

import com.example.projectmd6.model.Post;
import com.example.projectmd6.model.Users;
import com.example.projectmd6.service.IPostService;
import com.example.projectmd6.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/view/{idUser}")
    public ResponseEntity<Iterable<Post>> findAllPostByUser(@PathVariable Long idUser){
        Optional<Users> usersOptional = userService.findById(idUser);
        Iterable<Post> posts = postService.findAllByUser(usersOptional.get());
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Iterable<Users>> findAll() {
        List<Users> users = (List<Users>) userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Users> delete(@PathVariable Long id) {
        Optional<Users> usersOptional = userService.findById(id);
        if (!usersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.remove(id);
        return new ResponseEntity<>(usersOptional.get(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable Long id) {
        Optional<Users> usersOptional = userService.findById(id);
        if (!usersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usersOptional.get(), HttpStatus.OK);
    }
}
