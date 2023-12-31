package com.project.NarrowNet.repos;

import com.project.NarrowNet.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Optional<Long> userId);
}
