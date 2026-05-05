package com.jon.hyf_blog.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT DISTINCT a FROM Article a LEFT JOIN FETCH a.tags")
    List<Article> findAllWithTags();

    @Query("SELECT DISTINCT a FROM Article a LEFT JOIN FETCH a.tags WHERE a.id = ?1")
    Article findByIdWithTags(Long id);

}
