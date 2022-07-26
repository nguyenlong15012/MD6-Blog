package com.example.projectmd6.repository;

import com.example.projectmd6.model.Post;
import com.example.projectmd6.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {
    Iterable<Post> findAllByUser(Users users);

    List<Post>findPostsByTitleContaining(String title);

//    List<Post> findByTag_Name(String tagName);

    @Modifying
    @Query(value = "select * from post where status = 1;", nativeQuery = true)
    Iterable<Post> findAllByStatusPublic();

    @Modifying
    @Query(value = "select * from post where status = 1 and title like CONCAT('%',:title,'%');", nativeQuery = true)
    Iterable<Post> findAllByTitleContaining(@Param("title") String title);
}
