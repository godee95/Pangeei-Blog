package inhatc.spring.blog.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

@Component
public class JwtProvider {
    
    @Value("${secret-key}")
    private String secretKey;

    public String create(Long userId, String email) {
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS)); // 유효기간 1시간
        
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .claim("userId", userId)  // Add the user ID to the payload
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .compact();
    
        return jwt;
    }

    public JwtClaims validate(String jwt) {
        Claims claims = null;
    
        try {
            claims = Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(jwt).getBody();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    
        // Extract the user ID and email from the claims
        BigInteger userId = new BigInteger(claims.get("userId").toString());
        String email = claims.getSubject();
    
        return new JwtClaims(userId, email);
    }
}
