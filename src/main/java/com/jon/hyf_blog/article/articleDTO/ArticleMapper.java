package com.jon.hyf_blog.article.articleDTO;

import com.jon.hyf_blog.article.Article;
import com.jon.hyf_blog.tag.Tag;
import com.jon.hyf_blog.tag.TagDTO.TagSummaryDTO;
import com.jon.hyf_blog.tag.TagRepository;
import com.jon.hyf_blog.tag.tagExeption.NoTagExeption;
import com.jon.hyf_blog.tag.tagExeption.TagNotFoundExeption;
import com.jon.hyf_blog.user.User;
import com.jon.hyf_blog.user.UserRepository;
import com.jon.hyf_blog.user.userDTO.UserSummaryDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleMapper {

    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public ArticleResponseDTO toDto(Article article) {
        List<TagSummaryDTO> tagDtos = article.getTags()
                .stream()
                .map(tag -> new TagSummaryDTO(tag.getId(), tag.getTagName()))
                .toList();

        User user = article.getUser();
        UserSummaryDTO userSummaryDTO = new UserSummaryDTO(
                article.getUser().getId(),
                article.getUser().getUserName(),
                article.getUser().getRole()
        );

        return new ArticleResponseDTO(
                article.getId(),
                article.getTitle(),
                article.getBody(),
                userSummaryDTO,
                tagDtos
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
        List<Tag> tags = new ArrayList<>();
        User user = userRepository.findById(articleRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("No user id : " + articleRequestDTO.getUserId()));

        article.setTitle(articleRequestDTO.getTitle());
        article.setBody(articleRequestDTO.getBody());
        article.setUser(user);

        if (articleRequestDTO.getTagIds() == null) {
            throw new NoTagExeption();
        }

        for (Long tagId : articleRequestDTO.getTagIds()) {
            Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new TagNotFoundExeption(tagId));
            tags.add(tag);
        }

        article.setTags(tags);
        return article;
    }
}
