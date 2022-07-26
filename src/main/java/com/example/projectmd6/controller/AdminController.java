package com.example.projectmd6.controller;

import com.example.projectmd6.model.Post;
import com.example.projectmd6.service.IPostService;
import com.example.projectmd6.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;
    @GetMapping
    public ResponseEntity<?> pagePost(@PageableDefault(direction = Sort.Direction.ASC) Pageable pageable){
        Page<Post> postPage = postService.findAll(pageable);
        if(postPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postPage, HttpStatus.OK);
    }

//    public ResponseEntity<?> pagePót(@PageableDefault(sort = "nameCategory", direction = Sort.Direction.ASC) Pageable pageable){
//        Page<Post> categoryPage = postService.findAll(pageable);
//        if(categoryPage.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
//    }
}
