package com.jon.hyf_blog.tag.TagDTO;

import com.jon.hyf_blog.article.Article;
import com.jon.hyf_blog.tag.Tag;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagMapper {

    public TagResponseDTO toDto(Tag tag) {
        TagResponseDTO dto = new TagResponseDTO();
        dto.setId(tag.getId());
        dto.setTagName(tag.getTagName());

        List<ArticleSummaryDTO> articleSummaries = tag.getArticles().stream()
                .map(this::toArticleSummaryDto)
                .collect(Collectors.toList());
        dto.setArticles(articleSummaries);

        return dto;
    }

    private ArticleSummaryDTO toArticleSummaryDto(Article article) {
        ArticleSummaryDTO summary = new ArticleSummaryDTO(article.getId(), article.getTitle());
        summary.setId(article.getId());
        summary.setTitle(article.getTitle());
        return summary;
    }

    public Tag toEntity(TagRequestDTO tagRequestDTO) {
        Tag tag = new Tag();
        tag.setTagName(tagRequestDTO.getTagName());
        return tag;
    }
}