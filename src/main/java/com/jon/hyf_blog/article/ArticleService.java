package com.jon.hyf_blog.article;

import com.jon.hyf_blog.article.articleDTO.ArticleMapper;
import com.jon.hyf_blog.article.articleDTO.ArticleRequestDTO;
import com.jon.hyf_blog.article.articleDTO.ArticleResponseDTO;
import com.jon.hyf_blog.article.articleExeption.ArticleNotFoundExeption;
import com.jon.hyf_blog.article.articleExeption.NoArticleExeption;
import com.jon.hyf_blog.tag.Tag;
import com.jon.hyf_blog.tag.TagRepository;
import com.jon.hyf_blog.tag.tagExeption.TagNotFoundExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper mapper;
    private final TagRepository tagRepository;

    public List<ArticleResponseDTO> findAll(){
        List<Article> articleResponseDTOS = articleRepository.findAll();
        if(articleResponseDTOS.isEmpty()) {
            throw new NoArticleExeption();
        }
        return streamDto(articleRepository.findAll());
    }

    public Article findById(Long id){
        return articleRepository.findById(id)
                 .orElseThrow(() -> new ArticleNotFoundExeption(id));
    }

    public List<ArticleResponseDTO> findAllWithTags(){
        List<Article> articleResponseDTOS = articleRepository.findAllWithTags();
        if(articleResponseDTOS.isEmpty()) {
            throw new NoArticleExeption();
        }
        return streamDto(articleRepository.findAll());
    }

    public ArticleResponseDTO findByIdWithTags(Long id){
        Article articleResponseDTO = articleRepository.findByIdWithTags(id);
        if(articleResponseDTO == null) {
            throw new ArticleNotFoundExeption(id);
        }
        return mapper.toDto(articleResponseDTO);
    }

    public ArticleResponseDTO save(ArticleRequestDTO articleRequestDTO) {
        Article article = mapper.toEntity(articleRequestDTO);
        Article savedArticle = articleRepository.save(article);
        return mapper.toDto(savedArticle);
    }

    public ArticleResponseDTO update(Long id, ArticleRequestDTO articleRequestDTO) {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundExeption(id));

        existingArticle.setTitle(articleRequestDTO.getTitle());
        existingArticle.setBody(articleRequestDTO.getBody());

        List<Tag> tags = new ArrayList<>();
        if (articleRequestDTO.getTagIds() != null) {
            for (Long tagId : articleRequestDTO.getTagIds()) {
                Tag tag = tagRepository.findById(tagId)
                        .orElseThrow(() -> new TagNotFoundExeption(tagId));
                tags.add(tag);
            }
        }
        existingArticle.setTags(tags);

        Article updatedArticle = articleRepository.save(existingArticle);
        return mapper.toDto(updatedArticle);
    }

    public void delete(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundExeption(id));
        articleRepository.deleteById(article.getId());
    }

    private List<ArticleResponseDTO> streamDto(List<Article> articleList) {
        return articleList
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
