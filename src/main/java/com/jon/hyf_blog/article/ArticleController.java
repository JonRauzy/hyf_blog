package com.jon.hyf_blog.article;

import com.jon.hyf_blog.article.articleDTO.ArticleRequestDTO;
import com.jon.hyf_blog.article.articleDTO.ArticleResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public List<ArticleResponseDTO> findAll(){
        return articleService.findAllWithTags();
    }

    @GetMapping("/{id}")
    public ArticleResponseDTO findById(@PathVariable Long id){
        return articleService.findByIdWithTags(id);
    }

    @PostMapping
    public ArticleResponseDTO save(@RequestBody ArticleRequestDTO articleRequestDTO) {
        return articleService.save(articleRequestDTO);
    }

    @PutMapping("/{id}")
    public ArticleResponseDTO update(@PathVariable Long id, @RequestBody ArticleRequestDTO articleRequestDTO) {
        return articleService.update(id, articleRequestDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        articleService.delete(id);
        return "Article with id : " + id + " deleted";
    }
}
