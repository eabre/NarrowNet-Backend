package com.project.NarrowNet.repos;

import com.project.NarrowNet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
