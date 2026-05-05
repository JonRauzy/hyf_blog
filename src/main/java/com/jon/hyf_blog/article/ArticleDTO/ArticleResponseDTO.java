package com.jon.hyf_blog.article.ArticleDTO;

import java.util.List;

public record ArticleResponseDTO(
        Long id,
        String title,
        String body,
        List<TagSummaryDTO> tags
) {}
