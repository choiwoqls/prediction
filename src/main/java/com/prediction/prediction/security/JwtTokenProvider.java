package com.prediction.prediction.security;

import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.exception.MissingTokenException;
import com.prediction.prediction.exception.UnauthorizedException;
import com.prediction.prediction.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider{
    public static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiresIn}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        // Extract roles from userPrincipal and convert them to a list of role names
        List<String> roles = userPrincipal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
          try {
              String jwt =  Jwts.builder()
                      .id(userPrincipal.getEmail() + "")
                      .subject(userPrincipal.getEmail() + "")
                      .claim("user", userPrincipal)
                      .claim("roles", roles)
                      .issuedAt(new Date(System.currentTimeMillis()))
                      .expiration(expiryDate)
                      .signWith(JwtUtil.getKeyFromSecret(jwtSecret))
                      .compact();
              return jwt;
          }catch (Exception e){
              e.printStackTrace();
              throw new CustomException(e);

          }
    }


    public String getUserEmailFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                     .verifyWith(JwtUtil.getKeyFromSecret(jwtSecret))
                     .build()
                     .parseSignedClaims(token).getPayload();
            return claims.getSubject();
        }catch (Exception e){
            System.out.println(e.getClass());
            throw new CustomException(e);
        }
    }

    public String getClaimFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                     .verifyWith(JwtUtil.getKeyFromSecret(jwtSecret))
                     .build()
                     .parseSignedClaims(token).getPayload();
            return claims.get("roles").toString();
        }catch (Exception e){
            System.out.println(e.getClass());
            throw new CustomException(e);
        }
    }


    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith(JwtUtil.getKeyFromSecret(jwtSecret))
                    .build()
                    .parseSignedClaims(authToken).getPayload();

        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature", ex);
            throw new UnauthorizedException("Invalid Token");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token", ex);
            throw new UnauthorizedException("Invalid Token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token" + ex);
            throw new UnauthorizedException("Invalid Token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token" + ex);
            throw new UnauthorizedException("Invalid Token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty" + ex);
            throw new UnauthorizedException("Invalid Token");
        }
        return true;
    }



}
