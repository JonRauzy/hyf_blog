package com.jon.hyf_blog.tag;

import com.jon.hyf_blog.tag.TagDTO.TagRequestDTO;
import com.jon.hyf_blog.tag.TagDTO.TagResponseDTO;
import com.jon.hyf_blog.tag.TagDTO.TagSummaryDTO;
import jakarta.validation.Valid;
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

    @GetMapping("/{tagId}")
    public TagResponseDTO findByIdWithArticle(@PathVariable Long tagId) {
        return tagService.findByIdWithArticle(tagId);
    }

    @PostMapping
    public TagSummaryDTO save(@Valid @RequestBody TagRequestDTO tagRequestDTO){
        return tagService.save(tagRequestDTO);
    }

    @PutMapping("/{tagId}")
    public TagSummaryDTO update(
            @PathVariable Long tagId,
            @Valid @RequestBody TagRequestDTO tagRequestDTO
    ) {
        return tagService.update(tagId, tagRequestDTO);
    }

    @DeleteMapping("/{tagId}")
    public String delete(@PathVariable Long tagId) {
        tagService.delete(tagId);
        return "Tag id : " + tagId + " has been deleted";
    }
}
