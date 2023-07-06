package com.project.NarrowNet.services;

import com.project.NarrowNet.entities.Post;
import com.project.NarrowNet.entities.User;
import com.project.NarrowNet.repos.PostRepository;
import com.project.NarrowNet.requests.PostCreateRequest;
import com.project.NarrowNet.requests.PostUpdateRequest;
import com.project.NarrowNet.responses.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;

    private UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<PostResponse> getAllPostsByUserId(Optional<Long> userId) {
        List<Post> list;
        if(userId.isPresent()) {
           list = postRepository.findByUserId(userId);
        }else {
            list = postRepository.findAll();
        }
        return list.stream().map(p -> new PostResponse(p)).collect(Collectors.toList());
    }

    //return olarak PostResponse gelmeli
    public Post getPostById(Long postId) {
    	/*
        Post post = postRepository.findById(postId).orElse(null);
        if(post != null) {
            PostResponse postResponse = new PostResponse(post);
            return postResponse;
        }
        return null;
        */
    	return postRepository.findById(postId).orElse(null);
    }

    public Post createPost(PostCreateRequest postRequest) {
        User user = userService.getUserById(postRequest.getUserId());
        if(user == null)
            return null;
        Post post = new Post();
        post.setId(postRequest.getId());
        post.setTitle(postRequest.getTitle());
        post.setText(postRequest.getText());
        post.setUser(user);
        return postRepository.save(post);
    }

    public Post updatePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<Post> post =  postRepository.findById(postId);
        if(post.isPresent()) {
            Post foundPost = post.get();
            foundPost.setText(updatePost.getText());
            foundPost.setTitle(updatePost.getTitle());
            postRepository.save(foundPost);
            return foundPost;
        }
        return null;
    }

    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
