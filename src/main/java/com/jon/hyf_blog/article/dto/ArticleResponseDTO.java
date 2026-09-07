package com.jon.hyf_blog.article.dto;

import com.jon.hyf_blog.comment.dto.CommentSummaryDTO;
import com.jon.hyf_blog.tag.dto.TagSummaryDTO;
import com.jon.hyf_blog.user.dto.UserSummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponseDTO{
    private Long id;
    private String title;
    private String body;
    private UserSummaryDTO user;
    private List<TagSummaryDTO> tags;
    private List<CommentSummaryDTO> comments;
}
