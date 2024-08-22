package com.akshay.socialmediabackend.Controller;

import com.akshay.socialmediabackend.Models.Post;
import com.akshay.socialmediabackend.Services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    @Autowired
    PostServices postServices;

    @PostMapping("/posts/user/{userid}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userid) throws Exception {
        Post createdPost =postServices.createPost(post,userid);
        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/{postid}/user/{userid}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postid, @PathVariable Integer userid) throws Exception {
       String message= postServices.deletePost(postid,userid);
       return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
