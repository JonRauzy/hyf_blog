package com.jon.hyf_blog.tag.TagDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagRequestDTO {
    @NotBlank
    private String tagName;
}
