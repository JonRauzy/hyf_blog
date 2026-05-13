package com.jon.hyf_blog.user;

import com.jon.hyf_blog.article.Article;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    @NotBlank
    private String userName;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Article> articles = new ArrayList<>();
}

