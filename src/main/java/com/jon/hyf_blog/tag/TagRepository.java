package com.jon.hyf_blog.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query("SELECT DISTINCT t FROM Tag t LEFT JOIN FETCH t.articles")
    List<Tag> findAllWithArticle();

    @Query("SELECT DISTINCT t FROM Tag t LEFT JOIN FETCH t.articles WHERE t.id = ?1")
    Tag findByIdWithArticle(Long id);
}
