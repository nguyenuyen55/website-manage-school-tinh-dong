package com.example.websitemanageschooltinhdong.security.jwt;

import com.example.websitemanageschooltinhdong.security.service.CustomUserDetails;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String JWT_SECRET = "SwapNowOfBiti";
    private final long JWT_EXPIRATION = 1000*60*60*12*10;

    public String generateToken(CustomUserDetails myUserDetail) {
        Date now = new Date();
        Date expirationDate= new Date(now.getTime()+JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(myUserDetail.getUsername())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512,JWT_SECRET)
                .compact();
    }

    // lấy thông tin của user
    public String getUsernameFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String authToken) {
        boolean isValid=false;
        try{
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
           isValid=true;
        }catch (MalformedJwtException ex){
            System.out.println("Invalid JWT token");
        }catch (ExpiredJwtException ex){
            throw new JwtException("JWT token is expired", ex);
        }catch (UnsupportedJwtException ex){
            throw new JwtException("Unsupported JWT token");
        }catch (IllegalArgumentException ex){
            System.out.println("JWT claims string is empty.");
        }
        return isValid;
    }
}
