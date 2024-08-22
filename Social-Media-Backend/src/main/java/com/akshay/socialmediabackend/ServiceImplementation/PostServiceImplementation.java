package com.akshay.socialmediabackend.ServiceImplementation;

import com.akshay.socialmediabackend.Models.Post;
import com.akshay.socialmediabackend.Models.User;
import com.akshay.socialmediabackend.Repository.PostRepository;
import com.akshay.socialmediabackend.Repository.UserRepository;
import com.akshay.socialmediabackend.Services.PostServices;
import com.akshay.socialmediabackend.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostServices {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserServices userServices;

    @Autowired
    UserRepository userRepository;
    @Override
    public Post createPost(Post post, Integer userid) throws Exception {
        User user= userServices.findUserById(userid);
        Post newPost= new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImg(post.getImg());
        newPost.setVideo(post.getVideo());
//        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setUser(user);
        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postid, Integer userid) throws Exception {
        Post post=findPostById(postid);
        User user=userServices.findUserById(userid);

        if(post.getUser().getId() != user.getId()){
            throw new Exception("You can not delete this post");
        }
        postRepository.delete(post);
        return "Post Deleted";
    }

    @Override
    public List<Post> findPostByUserId(Integer userid) throws Exception {
        User user=userServices.findUserById(userid);
        return postRepository.findPostByUserId(user.getId());
    }

    @Override
    public Post findPostById(Integer postid) throws Exception {
        Optional<Post> post= postRepository.findById(postid);
        if(post.isEmpty())
        {
            throw new Exception("Post Not Found");
        }
        return post.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postid, Integer userid) throws Exception {
        Post post =findPostById(postid);
        User user=userServices.findUserById(userid);
        if(user.getSavedPost().contains(post))
        {
            user.getSavedPost().remove(post);
        }
        else
        {
            user.getSavedPost().add(post);
        }
        userRepository.save(user);
        return postRepository.save(post) ;
    }

    @Override
    public Post likePost(Integer postid, Integer userid) throws Exception {
        Post post=findPostById(postid);
        User user=userServices.findUserById(userid);

        if(post.getLikes().contains(user))
        {
            post.getLikes().remove(user);
        }
        else {
            post.getLikes().add(user);
        }

        return postRepository.save(post);
    }

    @Override
    public Integer totalLikes(Integer postid) throws Exception {
        Post post=findPostById(postid);
        return post.getLikes() != null ? post.getLikes().size() : 0;

    }
}
