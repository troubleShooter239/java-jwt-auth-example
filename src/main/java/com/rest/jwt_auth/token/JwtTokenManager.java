package com.rest.jwt_auth.token;

import static com.rest.jwt_auth.utils.Constants.ROLES;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class JwtTokenManager {

    private final SecretKey key;

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;

    public JwtTokenManager(@Value("${jwt.secret}") String secret) {
        this.key = Jwts.SIG.HS256.key().build();
    }

    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        claims.put(ROLES, roles);
        Date issuedDate = new Date();
        Date expiredDeate = new Date(issuedDate.getTime() + jwtLifetime.toMillis());
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(issuedDate)
                .expiration(expiredDeate)
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> getUserRoles(String token) {
        return getAllClaimsFromToken(token).get(ROLES, List.class);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
