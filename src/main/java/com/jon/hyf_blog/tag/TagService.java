package com.jon.hyf_blog.tag;

import com.jon.hyf_blog.tag.TagDTO.TagMapper;
import com.jon.hyf_blog.tag.TagDTO.TagRequestDTO;
import com.jon.hyf_blog.tag.TagDTO.TagResponseDTO;
import com.jon.hyf_blog.tag.TagDTO.TagSummaryDTO;
import com.jon.hyf_blog.util.exceptionHandler.NoResourceException;
import com.jon.hyf_blog.util.exceptionHandler.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper mapper;

    public List<TagSummaryDTO> findAll() {
        List<Tag> tags = tagRepository.findAll();

        if(tags.isEmpty()) {
            throw new NoResourceException(Tag.class);
        }

        return tags.stream()
                .map(tag -> new TagSummaryDTO(tag.getId(), tag.getTagName()))
                .toList();
    }

    public List<TagResponseDTO> findAllWithArticle() {
        List<Tag> tags = tagRepository.findAllWithArticle();

        if(tags.isEmpty()) {
            throw new NoResourceException(Tag.class);
        }

        return tags.stream()
                .map(mapper::toDto)
                .toList();
    }

    public TagResponseDTO findByIdWithArticle(Long tagId) {
        Tag tag = tagRepository.findByIdWithArticle(tagId);
        if(tag == null) {
            throw new ResourceNotFoundException(Tag.class, tagId);
        }
        return mapper.toDto(tag);
    }

    public TagSummaryDTO save(TagRequestDTO tagRequestDTO) {
        Tag tag = mapper.toEntity(tagRequestDTO);
        Tag savedTag = tagRepository.save(tag);
        return new TagSummaryDTO(savedTag.getId(), savedTag.getTagName());
    }

    public TagSummaryDTO update(Long tagId, TagRequestDTO tagRequestDTO) {
        Tag existingTag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException(Tag.class, tagId));

        existingTag.setTagName(tagRequestDTO.getTagName());
        Tag updatedTag = tagRepository.save(existingTag);

        return new TagSummaryDTO(updatedTag.getId(), updatedTag.getTagName());
    }

    public void delete(Long tagId) {
        Tag existingTag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException(Tag.class, tagId));
        tagRepository.deleteById(existingTag.getId());
    }
}