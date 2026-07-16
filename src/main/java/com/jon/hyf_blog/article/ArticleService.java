package com.jon.hyf_blog.article;

import com.jon.hyf_blog.article.articleDTO.ArticleMapper;
import com.jon.hyf_blog.article.articleDTO.ArticleRequestDTO;
import com.jon.hyf_blog.article.articleDTO.ArticleResponseDTO;
import com.jon.hyf_blog.tag.Tag;
import com.jon.hyf_blog.tag.TagRepository;
import com.jon.hyf_blog.user.User;
import com.jon.hyf_blog.user.UserRepository;
import com.jon.hyf_blog.util.exceptionHandler.NoResourceException;
import com.jon.hyf_blog.util.exceptionHandler.ResourceNotFoundException;
import com.jon.hyf_blog.util.exceptionHandler.WrongResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
            throw new NoResourceException(Article.class);
        }
        return streamDto(articleResponseDTOS);
    }

    public List<ArticleResponseDTO> findAllWithTags(){
        List<Article> articleResponseDTOS = articleRepository.findAllWithTags();
        if(articleResponseDTOS.isEmpty()) {

            throw new NoResourceException(Article.class);
        }
        return streamDto(articleResponseDTOS);
    }

    public List<ArticleResponseDTO> findAllWithComments() {
        List<Article> articleResponseDTOS = articleRepository.findAllWithComments();
        if(articleResponseDTOS.isEmpty()) {
            throw new NoResourceException(Article.class);
        }
        return streamDto(articleResponseDTOS);
    }

    public ArticleResponseDTO findByIdWithAll(Long articleId){
        Article article = articleRepository.findByIdWithAll(articleId)
                .orElseThrow(() -> new ResourceNotFoundException(Article.class, articleId));
        return articleMapper.toDto(article);
    }

    public ArticleResponseDTO findByIdWithTags(Long articleId){
        Article articleResponseDTO = articleRepository.findByIdWithTags(articleId)
                .orElseThrow(() -> new ResourceNotFoundException(Article.class, articleId));
        return articleMapper.toDto(articleResponseDTO);
    }

    public ArticleResponseDTO findByIdWithComments(Long articleId) {
        Article articleResponseDTO = articleRepository.findByIdWithComments(articleId)
                .orElseThrow(()-> new ResourceNotFoundException(Article.class, articleId));
        return articleMapper.toDto(articleResponseDTO);
    }

    public ArticleResponseDTO save(ArticleRequestDTO articleRequestDTO, User currentUser) {
        Article article = articleMapper.toEntity(articleRequestDTO);
        article.setUser(currentUser);
        Article savedArticle = articleRepository.save(article);
        return articleMapper.toDto(savedArticle);
    }

    public ArticleResponseDTO update(Long articleId, ArticleRequestDTO articleRequestDTO, User currentUser) {
        Article existingArticle = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException(Article.class, articleId));

        Set<Tag> tags = new HashSet<>();
        if (articleRequestDTO.getTagIds() != null) {
            for (Long tagId : articleRequestDTO.getTagIds()) {
                Tag tag = tagRepository.findById(tagId)
                        .orElseThrow(() -> new ResourceNotFoundException(Tag.class, tagId));
                tags.add(tag);
            }
        }

        if(!existingArticle.getUser().getId().equals(currentUser.getId())) {
            throw new WrongResource(User.class);
        }

        existingArticle.setTitle(articleRequestDTO.getTitle());
        existingArticle.setBody(articleRequestDTO.getBody());
        existingArticle.setTags(tags);

        Article updatedArticle = articleRepository.save(existingArticle);
        return articleMapper.toDto(updatedArticle);
    }

    public void delete(Long articleId, User currentUser) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException(Article.class, articleId));

        if(!article.getUser().getId().equals(currentUser.getId())) {
            throw new WrongResource(User.class);
        }

        articleRepository.deleteById(article.getId());
    }

    private List<ArticleResponseDTO> streamDto(List<Article> articleList) {
        return articleList
                .stream()
                .map(articleMapper::toDto)
                .toList();
    }
}
