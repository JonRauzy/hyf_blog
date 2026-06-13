package com.jon.hyf_blog.tag;

import com.jon.hyf_blog.tag.TagDTO.TagMapper;
import com.jon.hyf_blog.tag.TagDTO.TagRequestDTO;
import com.jon.hyf_blog.tag.TagDTO.TagResponseDTO;
import com.jon.hyf_blog.tag.TagDTO.TagSummaryDTO;
import com.jon.hyf_blog.util.exceptionHandler.NoRessourceExeption;
import com.jon.hyf_blog.util.exceptionHandler.RessourceNotFoundExeption;
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
            throw new NoRessourceExeption(Tag.class);
        }

        return tags.stream()
                .map(tag -> new TagSummaryDTO(tag.getId(), tag.getTagName()))
                .toList();
    }

    public List<TagResponseDTO> findAllWithArticle() {
        List<Tag> tags = tagRepository.findAllWithArticle();

        if(tags.isEmpty()) {
            throw new NoRessourceExeption(Tag.class);
        }

        return tags.stream()
                .map(mapper::toDto)
                .toList();
    }

    public TagResponseDTO findByIdWithArticle(Long id) {
        Tag tag = tagRepository.findByIdWithArticle(id);
        if(tag == null) {
            throw new RessourceNotFoundExeption(Tag.class, id);
        }
        return mapper.toDto(tag);
    }

    public TagSummaryDTO save(TagRequestDTO tagRequestDTO) {
        Tag tag = mapper.toEntity(tagRequestDTO);
        Tag savedTag = tagRepository.save(tag);
        return new TagSummaryDTO(savedTag.getId(), savedTag.getTagName());
    }

    public TagSummaryDTO update(Long id, TagRequestDTO tagRequestDTO) {
        Tag existingTag = tagRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundExeption(Tag.class, id));
        existingTag.setTagName(tagRequestDTO.getTagName());
        Tag updatedTag = tagRepository.save(existingTag);
        return new TagSummaryDTO(updatedTag.getId(), updatedTag.getTagName());
    }

    public void delete(Long id) {
        Tag existingTag = tagRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundExeption(Tag.class, id));
        tagRepository.deleteById(existingTag.getId());
    }
}