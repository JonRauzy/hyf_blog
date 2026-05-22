package com.jon.hyf_blog.article.articleDTO;

import com.jon.hyf_blog.tag.TagDTO.TagSummaryDTO;
import com.jon.hyf_blog.user.userDTO.UserSummaryDTO;

import java.util.List;

public record ArticleResponseDTO(
        Long id,
        String title,
        String body,
        UserSummaryDTO user,
        List<TagSummaryDTO> tags
) {}
