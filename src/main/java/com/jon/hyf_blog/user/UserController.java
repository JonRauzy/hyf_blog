package com.jon.hyf_blog.user;

import com.jon.hyf_blog.user.userDTO.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Getter
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserSummaryDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/with-articles")
    public List<UserResponseDTO> findAllWithArticles() {
        return userService.findAllWithArticles();
    }

    @PostMapping("/register")
    public UserSummaryDTO register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        return userService.register(registerRequestDTO);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return userService.login(loginRequestDTO);
    }
}
