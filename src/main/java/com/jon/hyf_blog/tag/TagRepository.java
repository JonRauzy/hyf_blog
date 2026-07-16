package com.jon.hyf_blog.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query("SELECT DISTINCT t FROM Tag t LEFT JOIN FETCH t.articles a LEFT JOIN FETCH a.user")
    List<Tag> findAllWithArticle();

    @Query("SELECT DISTINCT t FROM Tag t LEFT JOIN FETCH t.articles WHERE t.id = ?1")
    Optional<Tag> findByIdWithArticle(Long id);
}
