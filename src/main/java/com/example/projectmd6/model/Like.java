package com.example.projectmd6.model;

import javax.persistence.*;

@Entity
@Table(name = "like_post")
public class Like {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idLike;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private Users user;

        @ManyToOne
        @JoinColumn(name = "post_id")
        private Post post;

    public Like() {
    }

    public Like(Users user, Post post) {
        this.user = user;
        this.post = post;
    }

    public Like(Long idLike, Users user, Post post) {
        this.idLike = idLike;
        this.user = user;
        this.post = post;
    }

    public Long getIdLike() {
        return idLike;
    }

    public void setIdLike(Long idLike) {
        this.idLike = idLike;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}