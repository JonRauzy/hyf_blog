package com.jon.hyf_blog.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface
CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT DISTINCT c FROM Comment c LEFT JOIN FETCH c.article LEFT JOIN FETCH c.user")
    List<Comment> findAllWithArticleAndUser();
}
