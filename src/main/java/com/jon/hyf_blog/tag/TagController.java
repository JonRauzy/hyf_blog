package com.jon.hyf_blog.tag;

import com.jon.hyf_blog.tag.TagDTO.TagSummaryDTO;
import com.jon.hyf_blog.tag.TagDTO.TagRequestDTO;
import com.jon.hyf_blog.tag.TagDTO.TagResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public TagSummaryDTO save(@RequestBody TagRequestDTO tagRequestDTO){
        return tagService.save(tagRequestDTO);
    }

    @PutMapping("/{id}")
    public TagSummaryDTO update(@PathVariable Long id, @RequestBody TagRequestDTO tagRequestDTO) {
        return tagService.update(id, tagRequestDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        tagService.delete(id);
        return "Tag id : " + id + " has been deleted";
    }
}
