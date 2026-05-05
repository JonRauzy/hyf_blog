package com.jon.hyf_blog.tag.TagDTO;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class TagResponseDTO {
    private Long id;
    private String tagName;
    private List<ArticleSummaryDTO> articles; // Pas de boucle ici
}

