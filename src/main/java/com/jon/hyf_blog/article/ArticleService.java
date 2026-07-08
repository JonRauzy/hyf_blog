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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
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
        return articleMapper.toDto(articleResponseDTO);
    }

    public ArticleResponseDTO save(ArticleRequestDTO articleRequestDTO, User currentUser) {
        Article article = articleMapper.toEntity(articleRequestDTO);
        article.setUser(currentUser);
        Article savedArticle = articleRepository.save(article);
        return articleMapper.toDto(savedArticle);
    }

    public ArticleResponseDTO update(Long id, ArticleRequestDTO articleRequestDTO, User currentUser) {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundExeption(Article.class, id));

        Set<Tag> tags = new HashSet<>();
        if (articleRequestDTO.getTagIds() != null) {
            for (Long tagId : articleRequestDTO.getTagIds()) {
                Tag tag = tagRepository.findById(tagId)
                        .orElseThrow(() -> new RessourceNotFoundExeption(Tag.class, tagId));
                tags.add(tag);
            }
        }

        if(!existingArticle.getUser().getId().equals(currentUser.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You can only edit your own article"
            );
        }

        existingArticle.setTitle(articleRequestDTO.getTitle());
        existingArticle.setBody(articleRequestDTO.getBody());
        existingArticle.setTags(tags);

        Article updatedArticle = articleRepository.save(existingArticle);
        return articleMapper.toDto(updatedArticle);
    }

    public void delete(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundExeption(Article.class, id));
        articleRepository.deleteById(article.getId());
    }

    private List<ArticleResponseDTO> streamDto(List<Article> articleList) {
        return articleList
                .stream()
                .map(articleMapper::toDto)
                .toList();
    }
}
