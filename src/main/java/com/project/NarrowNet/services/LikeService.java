package com.project.NarrowNet.services;

import com.project.NarrowNet.entities.Like;
import com.project.NarrowNet.entities.Post;
import com.project.NarrowNet.entities.User;
import com.project.NarrowNet.repos.LikeRepository;
import com.project.NarrowNet.requests.LikeCreateRequest;
import com.project.NarrowNet.requests.LikeUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Like> getLikesByUserIdAndPostId(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()) {
            return likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return likeRepository.findByPostId(postId.get());
        }
        return likeRepository.findAll();
    }

    public Like getLikesById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createLike(LikeCreateRequest likeRequest) {
        User user = userService.getUserById(likeRequest.getUserId());
        Post post = postService.getPostById(likeRequest.getPostId());
        if(user != null && post != null){
            Like like = new Like();
            like.setId(likeRequest.getId());
            like.setPost(post);
            like.setUser(user);
            return likeRepository.save(like);
        }
        return null;
    }

    public Like updateLikeById(Long likeId, LikeUpdateRequest updateLike) {
        Optional<Like> like = likeRepository.findById(likeId);
        if (like.isPresent()) {
            Like likeToUpdate = like.get();
            return likeRepository.save(likeToUpdate);
        }
        return null;
    }

    public void deleteLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
