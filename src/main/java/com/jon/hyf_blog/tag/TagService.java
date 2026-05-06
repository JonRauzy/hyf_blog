package com.jon.hyf_blog.tag;

import com.jon.hyf_blog.article.ArticleDTO.TagSummaryDTO;
import com.jon.hyf_blog.tag.TagDTO.TagMapper;
import com.jon.hyf_blog.tag.TagDTO.TagRequestDTO;
import com.jon.hyf_blog.tag.TagDTO.TagResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper mapper;

    public List<TagSummaryDTO> findAll() {
        return tagRepository.findAll()
                .stream()
                .map(tag -> new TagSummaryDTO(tag.getId(), tag.getTagName()))
                .toList();
    }

    public List<TagResponseDTO> findAllWithArticle() {
        return tagRepository.findAllWithArticle()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public TagResponseDTO findByIdWithArticle(Long id) {
        return mapper.toDto(tagRepository.findByIdWithArticle(id));
    }

    public TagSummaryDTO save(TagRequestDTO tagRequestDTO) {
        Tag tag = mapper.toEntity(tagRequestDTO);
        Tag savedTag = tagRepository.save(tag);
        return new TagSummaryDTO(savedTag.getId(), savedTag.getTagName());
    }

    public TagResponseDTO update(Long id, TagRequestDTO tagRequestDTO) {
        Tag existingTag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found with id: " + id));
        existingTag.setTagName(tagRequestDTO.getTagName());
        Tag updatedTag = tagRepository.save(existingTag);
        return mapper.toDto(updatedTag);
    }

    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}