package com.jon.hyf_blog.user;

import com.jon.hyf_blog.user.userDTO.*;
import com.jon.hyf_blog.util.security.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Getter
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    private final JwtUtils jwtUtils;

    public List<UserSummaryDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(mapper::toSummaryDTO)
                .toList();
    }

    public List<UserResponseDTO> findAllWithArticles(){
        return userRepository.findAllWithArticles()
                .stream()
                .filter(user -> !user.getArticles().isEmpty())
                .map(mapper::toDto)
                .toList();
    }

    public UserSummaryDTO register(RegisterRequestDTO registerRequestDTO) {
        if(userRepository.existsByEmail(registerRequestDTO.getEmail())){
            throw new RuntimeException("User exist with " + registerRequestDTO.getEmail());
        }

        String hashedPassword = passwordEncoder.encode(registerRequestDTO.getPassword());
        registerRequestDTO.setPassword(hashedPassword);
        User user = userRepository.save(mapper.toEntity(registerRequestDTO));
        return mapper.toSummaryDTO(user);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(()-> new RuntimeException("Nope"));

        if(!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())){
            throw new RuntimeException("Nope");
        }

        Map<String, Object> claims = Map.of(
                "userId", user.getId(),
                "role", user.getRole()
        );

        //call JwtUtils to generate a JWTToken
        String token = jwtUtils.generateToken(user.getEmail(), claims);

        //return the user the token
        return new LoginResponseDTO(token);
    }
}
