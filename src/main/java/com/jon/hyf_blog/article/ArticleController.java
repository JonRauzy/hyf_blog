package com.jon.hyf_blog.article;

import com.jon.hyf_blog.article.articleDTO.ArticleRequestDTO;
import com.jon.hyf_blog.article.articleDTO.ArticleResponseDTO;
import com.jon.hyf_blog.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public List<ArticleResponseDTO> findAll(){
        return articleService.findAll();
    }

    @GetMapping("/{id}")
    public ArticleResponseDTO findById(@PathVariable Long id){
        return articleService.findByIdWithTags(id);
    }

    @PostMapping
    public ArticleResponseDTO save(
            @Valid @RequestBody ArticleRequestDTO articleRequestDTO,
            @AuthenticationPrincipal User currentUser
    ) {
        return articleService.save(articleRequestDTO, currentUser);
    }

    @PutMapping("/{id}")
    public ArticleResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody ArticleRequestDTO articleRequestDTO,
            @AuthenticationPrincipal User currentUser
    ) {
        return articleService.update(id, articleRequestDTO, currentUser);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long articleId) {
        articleService.delete(articleId);
        return "Article with id : " + articleId + " deleted";
    }
}
