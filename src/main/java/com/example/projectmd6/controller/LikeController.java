package com.example.projectmd6.controller;

import com.example.projectmd6.model.Like;
import com.example.projectmd6.service.ILikeService;
import com.example.projectmd6.service.IPostService;
import com.example.projectmd6.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private ILikeService likeService;

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

    @GetMapping("/{idPost}")
    public ResponseEntity<Integer> countLikePost(@PathVariable("idPost") Long idPost) {
        return new ResponseEntity<>(likeService.countByPostIdPost(idPost), HttpStatus.OK);
    }

    @GetMapping("/{idUser}/{idPost}")
    public ResponseEntity<Integer> likeUnlikePost(@PathVariable("idUser") Long idUser, @PathVariable("idPost") Long idPost) {
        Like likePost = likeService.findByPostIdPostAndIdUser(idPost, idUser);
        if (likePost != null) {
            likeService.deleteLikePost(likePost);
        } else {
            likeService.save(new Like(userService.findById(idUser).get(), postService.findById(idPost).get()));
        }
        Integer likeTotal = likeService.countByPostIdPost(idPost);
        return new ResponseEntity<>(likeTotal, HttpStatus.OK);
    }

}
