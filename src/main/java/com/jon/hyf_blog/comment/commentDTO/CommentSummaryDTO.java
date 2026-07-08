package com.jon.hyf_blog.comment.commentDTO;

import com.jon.hyf_blog.article.articleDTO.ArticleSummaryDTO;
import com.jon.hyf_blog.user.userDTO.UserSummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentSummaryDTO {
    private Long id;
    private String body;
    private ArticleSummaryDTO article;
    private UserSummaryDTO user;
}
