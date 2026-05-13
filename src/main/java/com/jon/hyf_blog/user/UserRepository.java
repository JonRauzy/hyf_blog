package com.jon.hyf_blog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.articles")
    List<User> findAllWithArticles();

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
