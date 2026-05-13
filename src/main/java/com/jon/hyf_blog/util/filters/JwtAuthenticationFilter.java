package com.jon.hyf_blog.util.filters;

import com.jon.hyf_blog.user.User;
import com.jon.hyf_blog.user.UserRepository;
import com.jon.hyf_blog.util.security.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { // implements Filter

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//  public void doFilter(ServletRequest req, ServletResponse response,  FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest request = (HttpServletRequest) req;
        String header = request.getHeader("Authorization");

        //Bearer asjdalsdakjsdak.akjshdkasldks.lakjshdkajslkd
        if(header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if(jwtUtils.isValid(token)) {
                String email = jwtUtils.getSubject(token);
                User user = userRepository.findByEmail(email).orElse(null);

                if(user != null) {
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());

                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken(
                            user,       //principal is the logged in user
                            null,
                            List.of(authority)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        //always continue with calling other filters
        filterChain.doFilter(request, response);
    }
}
