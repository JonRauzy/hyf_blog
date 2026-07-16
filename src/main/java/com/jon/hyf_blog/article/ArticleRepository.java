package com.jon.hyf_blog.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT DISTINCT a FROM Article a " +
            "LEFT JOIN FETCH a.user " +
            "LEFT JOIN FETCH a.tags " +
            "LEFT JOIN FETCH a.comments c " +
            "LEFT JOIN FETCH c.user")
    List<Article> findAllWithAll();

    @Query("""
            SELECT DISTINCT a
            FROM Article a
            LEFT JOIN FETCH a.tags
            LEFT JOIN FETCH a.user
        """)
    List<Article> findAllWithTags();

    @Query("SELECT DISTINCT a " +
            "FROM Article a " +
            "LEFT JOIN FETCH a.comments")
    List<Article> findAllWithComments();

    @Query("SELECT DISTINCT a FROM Article a LEFT JOIN FETCH a.tags WHERE a.id = ?1")
    Optional<Article> findByIdWithTags(Long articleId);

    @Query("SELECT DISTINCT a FROM Article a LEFT JOIN FETCH a.comments WHERE a.id = ?1")
    Optional<Article> findByIdWithComments(Long articleId);

    @Query("""
            SELECT DISTINCT a
            FROM Article a
            LEFT JOIN FETCH a.tags
            LEFT JOIN FETCH a.user
            WHERE a.id = ?1
        """)
    Optional<Article> findByIdWithAll(Long articleId);
}
