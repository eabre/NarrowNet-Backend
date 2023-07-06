package com.project.NarrowNet.controllers;

import com.project.NarrowNet.entities.Like;
import com.project.NarrowNet.requests.LikeCreateRequest;
import com.project.NarrowNet.requests.LikeUpdateRequest;
import com.project.NarrowNet.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<Like> getLikes(@RequestParam Optional<Long> userId,
    @RequestParam Optional<Long> postId) {
        return likeService.getLikesByUserIdAndPostId(userId,  postId);
    }

    @GetMapping("/{likeId}")
    public Like getLike(@PathVariable Long likeId) {
        return likeService.getLikesById(likeId);
    }

    @PostMapping
    public Like createLike(@RequestBody LikeCreateRequest likeRequest) {
        return likeService.createLike(likeRequest);
    }

    @PutMapping("/{likeId}")
    public Like updateLike(@PathVariable Long likeId, @RequestBody LikeUpdateRequest updateLike) {
        return likeService.updateLikeById(likeId, updateLike);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLike(@PathVariable Long likeId) {
        likeService.deleteLikeById(likeId);
    }
}
