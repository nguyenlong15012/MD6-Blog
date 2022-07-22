package com.example.projectmd6.repository;

import com.example.projectmd6.model.Post;
import com.example.projectmd6.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {

    //danh sach cong ty khong khoa
    @Query(value = "select * from post where status = 1;", nativeQuery = true)
    Iterable<Post> findAllUnlockPost();

    //danh sach cong ty bi khoa
    @Query(value = "select * from post where status = 0;", nativeQuery = true)
    Iterable<Post> findAllLockPost();

    Iterable<Post> findAllByUser(Users users);

    List<Post>findPostsByTitleContaining(String title);

}
