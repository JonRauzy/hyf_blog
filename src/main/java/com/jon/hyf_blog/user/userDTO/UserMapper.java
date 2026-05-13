package com.jon.hyf_blog.user.userDTO;

import com.jon.hyf_blog.article.articleDTO.ArticleSummaryDTO;
import com.jon.hyf_blog.tag.TagDTO.TagMapper;
import com.jon.hyf_blog.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    private final TagMapper tagMapper;

    public UserMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    public User toEntity(RegisterRequestDTO registerRequestDTO) {
        User user = new User();

        user.setUserName(registerRequestDTO.getUserName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(registerRequestDTO.getPassword());
        user.setRole(registerRequestDTO.getRole());

        return user;
    }

    public UserSummaryDTO toSummaryDTO(User user) {
       return new UserSummaryDTO(
               user.getId(),
               user.getUserName(),
               user.getRole()
       );
    }

    public UserResponseDTO toDto(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());

        List<ArticleSummaryDTO> articleSummaries = user.getArticles()
                .stream()
                .map(tagMapper::toArticleSummaryDto)
                .toList();
        dto.setArticles(articleSummaries);

        return dto;

    }

}
