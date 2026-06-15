package com.jon.hyf_blog.article;

import com.jon.hyf_blog.article.articleDTO.ArticleMapper;
import com.jon.hyf_blog.article.articleDTO.ArticleRequestDTO;
import com.jon.hyf_blog.article.articleDTO.ArticleResponseDTO;
import com.jon.hyf_blog.tag.Tag;
import com.jon.hyf_blog.tag.TagRepository;
import com.jon.hyf_blog.user.User;
import com.jon.hyf_blog.user.UserRepository;
import com.jon.hyf_blog.util.exceptionHandler.NoRessourceExeption;
import com.jon.hyf_blog.util.exceptionHandler.RessourceNotFoundExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper mapper;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public List<ArticleResponseDTO> findAll(){
        List<Article> articleResponseDTOS = articleRepository.findAllWithAll();
        if(articleResponseDTOS.isEmpty()) {
            throw new NoRessourceExeption(Article.class);
        }
        return streamDto(articleResponseDTOS);
    }

    public List<ArticleResponseDTO> findAllWithTags(){
        List<Article> articleResponseDTOS = articleRepository.findAllWithTags();
        if(articleResponseDTOS.isEmpty()) {
            throw new NoRessourceExeption(Article.class);
        }
        return streamDto(articleResponseDTOS);
    }

    public List<ArticleResponseDTO> findAllWithComments() {
        List<Article> articleResponseDTOS = articleRepository.findAllWithComments();
        if(articleResponseDTOS.isEmpty()) {
            throw new NoRessourceExeption(Article.class);
        }
        return streamDto(articleResponseDTOS);
    }

    public Article findById(Long id){
        return articleRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundExeption(Article.class, id));
    }

    public ArticleResponseDTO findByIdWithTags(Long id){
        Article articleResponseDTO = articleRepository.findByIdWithTags(id);
        if(articleResponseDTO == null) {
            throw new RessourceNotFoundExeption(Article.class, id);
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
                .orElseThrow(() -> new RessourceNotFoundExeption(Article.class, id));

        existingArticle.setTitle(articleRequestDTO.getTitle());
        existingArticle.setBody(articleRequestDTO.getBody());

        Set<Tag> tags = new HashSet<>();
        if (articleRequestDTO.getTagIds() != null) {
            for (Long tagId : articleRequestDTO.getTagIds()) {
                Tag tag = tagRepository.findById(tagId)
                        .orElseThrow(() -> new RessourceNotFoundExeption(Tag.class, tagId));
                tags.add(tag);
            }
        }
        existingArticle.setTags(tags);

        User user = userRepository.findById(articleRequestDTO.getUserId())
                .orElseThrow(() -> new RessourceNotFoundExeption(User.class, articleRequestDTO.getUserId()));
        existingArticle.setUser(user);

        Article updatedArticle = articleRepository.save(existingArticle);
        return mapper.toDto(updatedArticle);
    }

    public void delete(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundExeption(Article.class, id));
        articleRepository.deleteById(article.getId());
    }

    private List<ArticleResponseDTO> streamDto(List<Article> articleList) {
        return articleList
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
