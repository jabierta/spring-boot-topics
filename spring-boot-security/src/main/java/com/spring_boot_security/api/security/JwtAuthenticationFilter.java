package com.spring_boot_security.api.security;

import com.spring_boot_security.api.domain.User;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtService jwtService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws ServletException, IOException {
    final String authorizationHeader = httpServletRequest.getHeader("Authorization");
    User user = null;
    String token = null;

    // I don't know why this is important, when we might be able to use the SecurityContextHolder
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      token = authorizationHeader.substring(7);
      try {
        user = jwtService.parseToken(token);
      } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
      }
    }

    // This doesn't make sense since shouldn't the context already have the userName details after
    // the filter?
    if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      try {
        Claims claims = jwtService.extractAllClaims(token);
        Date expiration = claims.getExpiration();
        if (!expiration.before(new Date())) {
          UserDetails userDetails =
              new org.springframework.security.core.userdetails.User(
                  user.getUserName(),
                  user.getPassword(),
                  AuthorityUtils.createAuthorityList(user.getRole()));

          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
              new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities());

          usernamePasswordAuthenticationToken.setDetails(
              new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
      } catch (Exception ignored) {
      }
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
