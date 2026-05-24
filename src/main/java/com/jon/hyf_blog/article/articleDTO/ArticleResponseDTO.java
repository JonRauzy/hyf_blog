package com.jon.hyf_blog.article.articleDTO;

import com.jon.hyf_blog.tag.TagDTO.TagSummaryDTO;
import com.jon.hyf_blog.user.userDTO.UserSummaryDTO;
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
}
