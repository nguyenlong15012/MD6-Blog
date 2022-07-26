package com.example.projectmd6.controller;

import com.example.projectmd6.model.Comment;
import com.example.projectmd6.repository.ICommentRepository;
import com.example.projectmd6.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @GetMapping()
    public ResponseEntity<Iterable<Comment>> findAll(){
        Iterable<Comment> comments = commentService.findAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Comment> createCmt(@RequestBody Comment comment){
        commentService.save(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping({"/{idCmt}"})
    public ResponseEntity<Comment> deleteCmt(@PathVariable Long idCmt){
        commentService.remove(idCmt);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{idCmt}")
    public ResponseEntity<Comment> updateCmt (@PathVariable Long idCmt, @RequestBody Comment comment){
        Optional<Comment> commentOptional = commentService.findById(idCmt);
        comment.setIdComment(commentOptional.get().getIdComment());
        commentService.save(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
