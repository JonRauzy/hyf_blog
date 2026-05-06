package com.jon.hyf_blog.tag;

import com.jon.hyf_blog.article.ArticleDTO.TagSummaryDTO;
import com.jon.hyf_blog.tag.TagDTO.TagResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping
    public List<TagSummaryDTO> findAll(){
        return tagService.findAll();
    }

    @GetMapping("/with-articles")
    public List<TagResponseDTO> findAllWithArticle(){
        return tagService.findAllWithArticle();
    }

    @GetMapping("/{id}")
    public TagResponseDTO findByIdWithArticle(@PathVariable Long id) {
        return tagService.findByIdWithArticle(id);
    }
}
