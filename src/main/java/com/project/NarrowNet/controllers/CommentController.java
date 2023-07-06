package com.project.NarrowNet.controllers;

import com.project.NarrowNet.entities.Comment;
import com.project.NarrowNet.requests.CommentCreateRequest;
import com.project.NarrowNet.requests.CommentUpdateRequest;
import com.project.NarrowNet.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getComments(@RequestParam Optional<Long> userId,
    @RequestParam Optional<Long> postId) {
        return commentService.getCommentsByUserIdAndPostId(userId,  postId);
    }

    @GetMapping("/{commentId}")
    public Comment getComment(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest commentRequest) {
        return commentService.createComment(commentRequest);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest updateComment) {
        return commentService.updateCommentById(commentId, updateComment);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteCommentById(commentId);
    }
}
