package com.jon.hyf_blog.article.ArticleDTO;

import com.jon.hyf_blog.tag.TagDTO.TagSummaryDTO;

import java.util.List;

public record ArticleResponseDTO(
        Long id,
        String title,
        String body,
        List<TagSummaryDTO> tags
) {}
