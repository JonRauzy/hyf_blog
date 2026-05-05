package com.jon.hyf_blog.article;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.jon.hyf_blog.article.ArticleDTO.ArticleResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
