package com.akshay.socialmediabackend.Repository;

import com.akshay.socialmediabackend.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query("SELECT p from Post p where p.user.id=:userid")
    List<Post> findPostByUserId(Integer userid);
}
