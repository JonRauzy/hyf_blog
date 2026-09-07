package com.jon.hyf_blog.article.dto;

import com.jon.hyf_blog.article.Article;
import com.jon.hyf_blog.comment.dto.CommentSummaryDTO;
import com.jon.hyf_blog.tag.Tag;
import com.jon.hyf_blog.tag.dto.TagSummaryDTO;
import com.jon.hyf_blog.tag.TagRepository;
import com.jon.hyf_blog.user.dto.UserSummaryDTO;
import com.jon.hyf_blog.exception.NoResourceException;
import com.jon.hyf_blog.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ArticleMapper {

    private final TagRepository tagRepository;

    public ArticleResponseDTO toDto(Article article) {
        List<TagSummaryDTO> tagDtos = article.getTags()
                .stream()
                .map(tag -> new TagSummaryDTO(tag.getId(), tag.getTagName()))
                .toList();
        
        UserSummaryDTO userDTO = new UserSummaryDTO(
                article.getUser().getId(),
                article.getUser().getUserName(),
                article.getUser().getRole()
        );

        List<CommentSummaryDTO> commentDTO = article.getComments()
                .stream()
                .map(comment -> new CommentSummaryDTO(
                        comment.getId(),
                        comment.getBody(),
                        new UserSummaryDTO(
                                comment.getUser().getId(),
                                comment.getUser().getUserName(),
                                comment.getUser().getRole()),
                        comment.getCreatedAt()
                        )
                )
                .toList();

        return new ArticleResponseDTO(
                article.getId(),
                article.getTitle(),
                article.getBody(),
                userDTO,
                tagDtos,
                commentDTO
        );
    }

    public ArticleSummaryDTO toArticleSummaryDto(Article article) {
        ArticleSummaryDTO summary = new ArticleSummaryDTO(article.getId(), article.getTitle());
        summary.setId(article.getId());
        summary.setTitle(article.getTitle());
        return summary;
    }

    public Article toEntity(ArticleRequestDTO articleRequestDTO) {
        Article article = new Article();
        Set<Tag> tags = new HashSet<>();

        article.setTitle(articleRequestDTO.getTitle());
        article.setBody(articleRequestDTO.getBody());

        if (articleRequestDTO.getTagIds() == null) {
            throw new NoResourceException(Tag.class);
        }

        for (Long tagId : articleRequestDTO.getTagIds()) {
            Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new ResourceNotFoundException(Tag.class, tagId));
            tags.add(tag);
        }

        article.setTags(tags);
        return article;
    }
}
