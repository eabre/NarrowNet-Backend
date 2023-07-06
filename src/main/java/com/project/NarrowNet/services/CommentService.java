package com.project.NarrowNet.services;


import com.project.NarrowNet.entities.Comment;
import com.project.NarrowNet.entities.Post;
import com.project.NarrowNet.entities.User;
import com.project.NarrowNet.repos.CommentRepository;
import com.project.NarrowNet.requests.CommentCreateRequest;
import com.project.NarrowNet.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;
    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getCommentsByUserIdAndPostId(Optional<Long> userId,
    Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        }
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createComment(CommentCreateRequest commentRequest) {
        User user = userService.getUserById(commentRequest.getUserId());
        Post post = postService.getPostById(commentRequest.getPostId());
        if(user != null && post != null){
            Comment comment = new Comment();
            comment.setId(commentRequest.getId());
            comment.setPost(post);
            comment.setUser(user);
            comment.setText(commentRequest.getText());
            return commentRepository.save(comment);
        }
        return null;
    }

    public Comment updateCommentById(Long commentId, @RequestBody CommentUpdateRequest updateComment) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(updateComment.getText());
            return commentRepository.save(commentToUpdate);
        }
        return null;
    }

    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
