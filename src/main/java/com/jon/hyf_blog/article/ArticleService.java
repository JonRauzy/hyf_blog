package com.jon.hyf_blog.article;

import com.jon.hyf_blog.article.ArticleDTO.ArticleMapper;
import com.jon.hyf_blog.article.ArticleDTO.ArticleRequestDTO;
import com.jon.hyf_blog.article.ArticleDTO.ArticleResponseDTO;
import com.jon.hyf_blog.tag.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper mapper;

    public List<ArticleResponseDTO> findAll(){
        return streamDto(articleRepository.findAll());
    }

    public List<ArticleResponseDTO> findAllWithTags(){
        return streamDto(articleRepository.findAllWithTags());
    }

    public ArticleResponseDTO findByIdWithTags(Long id){
        return mapper.toDto(articleRepository.findByIdWithTags(id));
    }

    public ArticleResponseDTO save(ArticleRequestDTO articleRequestDTO) {
        Article article = mapper.toEntity(articleRequestDTO);
        Article savedArticle = articleRepository.save(article);
        return mapper.toDto(savedArticle);
    }

    public ArticleResponseDTO update(Long id, ArticleRequestDTO articleRequestDTO) {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));

        existingArticle.setTitle(articleRequestDTO.getTitle());
        existingArticle.setBody(articleRequestDTO.getBody());

        List<Tag> tags = new ArrayList<>();
        if (articleRequestDTO.getTagIds() != null) {
            for (Long tagId : articleRequestDTO.getTagIds()) {
                Tag tag = new Tag();
                tag.setId(tagId);
                tags.add(tag);
            }
        }
        existingArticle.setTags(tags);

        Article updatedArticle = articleRepository.save(existingArticle);
        return mapper.toDto(updatedArticle);
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    private List<ArticleResponseDTO> streamDto(List<Article> articleList) {
        return articleList
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
