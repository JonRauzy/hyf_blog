package com.jon.hyf_blog.comment.commentDTO;

import com.jon.hyf_blog.article.articleDTO.ArticleResponseDTO;
import com.jon.hyf_blog.article.articleDTO.ArticleSummaryDTO;
import com.jon.hyf_blog.user.userDTO.UserSummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {
    private Long id;
    private String body;
    private LocalDateTime createdAt;
    private UserSummaryDTO user;
    private ArticleSummaryDTO article;
}
