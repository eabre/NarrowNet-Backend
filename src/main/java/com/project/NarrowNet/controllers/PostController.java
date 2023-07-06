package com.project.NarrowNet.controllers;

import com.project.NarrowNet.entities.Post;
import com.project.NarrowNet.requests.PostCreateRequest;
import com.project.NarrowNet.requests.PostUpdateRequest;
import com.project.NarrowNet.responses.PostResponse;
import com.project.NarrowNet.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPostsByUserId(userId);
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest postRequest){
        return postService.createPost(postRequest);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost){
        return postService.updatePostById(postId, updatePost);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePostById(postId);
    }
}
