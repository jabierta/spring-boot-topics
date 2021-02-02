package com.spring_boot_security.api.security;

import com.spring_boot_security.api.domain.User;
import com.spring_boot_security.api.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
  private final UserService userService;

  @Value(value = "${jwt.issuer}")
  private String issuer;

  @Value(value = "${jwt.token.secret}")
  private String signature;

  public String generateToken(User user) {
    JwtBuilder builder =
        Jwts.builder()
            .setSubject(user.getId().toString())
            .setIssuer(issuer)
            .compressWith(CompressionCodecs.DEFLATE)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(SignatureAlgorithm.HS256, signature);

    return builder.compact();
  }

  public User parseToken(String token) throws Exception {
    Integer id;

    Claims claims =
        Jwts.parser()
            .requireIssuer(issuer)
            .setSigningKey(signature)
            .parseClaimsJws(token)
            .getBody();

    id = Integer.parseInt(claims.getSubject());

    return userService.findById(id);
  }

  public Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(signature).parseClaimsJws(token).getBody();
  }
}
