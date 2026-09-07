package com.jon.hyf_blog.user;

import com.jon.hyf_blog.user.dto.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/{userId}")
    public UserSummaryDTO findById(@PathVariable Long userId){
        return userService.findById(userId);
    }

    @PostMapping("/register")
    public UserSummaryDTO register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        return userService.register(registerRequestDTO);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return userService.login(loginRequestDTO);
    }

//    @PostMapping("/logout")
//    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
//        return userService.logout(loginRequestDTO);
//    }

    @PutMapping("/{userId}")
    public UserSummaryDTO updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateUserRequestDTO updateUserRequestDTO,
            @AuthenticationPrincipal User currentUser
    ) {
        return userService.updateUser(userId, updateUserRequestDTO, currentUser);
    }
}
