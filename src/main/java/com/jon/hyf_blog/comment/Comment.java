package com.jon.hyf_blog.comment;

import com.jon.hyf_blog.article.Article;
import com.jon.hyf_blog.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    @CreationTimestamp
    @Column(name="CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="UPDATED_AT", insertable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}
