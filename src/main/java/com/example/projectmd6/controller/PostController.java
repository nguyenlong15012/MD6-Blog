package com.example.projectmd6.controller;

import com.example.projectmd6.model.Comment;
import com.example.projectmd6.model.Post;
import com.example.projectmd6.service.ICommentService;
import com.example.projectmd6.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private IPostService postService;

    @Autowired
    private ICommentService commentService;

//    @GetMapping
//    public ResponseEntity<Iterable<Post>> findAll() {
//        List<Post> posts = (List<Post>) postService.findAll();
//        if (posts.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(posts, HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<?> findAll(@PageableDefault(sort = "id_post", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Post> postsPage = postService.findAll(pageable);
        if(postsPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postsPage, HttpStatus.OK);
    }
    @GetMapping("/find-all-public-status")
    public ResponseEntity<Iterable<Post>> findAllByStatusPublic() {
        List<Post> posts = (List<Post>) postService.findAllByStatusPublic();
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Post> add(@RequestBody Post post) {
        post.setCreateAt(Date.valueOf(java.time.LocalDate.now() + ""));
        postService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        post.setIdPost(postOptional.get().getIdPost());
        postService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> delete(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.remove(id);
        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
    }

    @GetMapping("find-by-title/{title}")
    public ResponseEntity<Iterable<Post>> findByTitle(@PathVariable String title) {
        List<Post> posts = postService.findPostsByTitleContaining(title);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

//    @GetMapping("find-by-tag/{tagName}")
//    public ResponseEntity<Iterable<Post>> findByTag(@PathVariable String tagName) {
//        List<Post> posts = postService.findByTag_Name(tagName);
//        if (posts.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(posts, HttpStatus.OK);
//    }

    // show comment theo bài post
    @GetMapping("/view-comment/{idPost}")
    public ResponseEntity findAllCommentByPost(@PathVariable Long idPost){
        Optional<Post> postOptional = postService.findById(idPost);
        if (!postOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<Comment> comments = commentService.findAllByPost(postOptional.get());
        return new ResponseEntity<>(comments,HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Iterable<Post>> findAllByTitleContaining(@RequestParam String title) {
        Iterable<Post> posts = postService.findAllByTitleContaining(title);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
