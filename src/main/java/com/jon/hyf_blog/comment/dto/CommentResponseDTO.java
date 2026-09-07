package com.jon.hyf_blog.comment.dto;

import com.jon.hyf_blog.article.dto.ArticleSummaryDTO;
import com.jon.hyf_blog.user.dto.UserSummaryDTO;
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
