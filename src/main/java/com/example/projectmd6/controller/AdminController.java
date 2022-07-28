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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;
    @GetMapping
    public ResponseEntity<?> pagePost(Pageable pageable){
        Page<Post> postPage = postService.findAllOrderById(pageable);
        if(postPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postPage, HttpStatus.OK);
    }
////    xóa bìa viết
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Post> delete(@PathVariable Long id) {
//        Optional<Post> postOptional = postService.findById(id);
//        if (!postOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        postService.remove(id);
//        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
//    }

//    public ResponseEntity<?> pagePost(@PageableDefault(sort = "nameCategory", direction = Sort.Direction.ASC) Pageable pageable){
//        Page<Post> categoryPage = postService.findAll(pageable);
//        if(categoryPage.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
//    }
}
