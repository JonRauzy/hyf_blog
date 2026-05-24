package com.jon.hyf_blog.tag.TagDTO;

import com.jon.hyf_blog.article.articleDTO.ArticleMapper;
import com.jon.hyf_blog.article.articleDTO.ArticleSummaryDTO;
import com.jon.hyf_blog.tag.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TagMapper {

    private final ArticleMapper articleMapper;

    public TagResponseDTO toDto(Tag tag) {
        TagResponseDTO dto = new TagResponseDTO();
        dto.setId(tag.getId());
        dto.setTagName(tag.getTagName());

        List<ArticleSummaryDTO> articleSummaries = tag.getArticles()
                .stream()
                .map(articleMapper::toArticleSummaryDto)
                .toList();
        dto.setArticles(articleSummaries);

        return dto;
    }

    public Tag toEntity(TagRequestDTO tagRequestDTO) {
        Tag tag = new Tag();
        tag.setTagName(tagRequestDTO.getTagName());
        return tag;
    }
}