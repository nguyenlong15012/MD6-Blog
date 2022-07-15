package com.example.projectmd6.security.jwt;

import com.example.projectmd6.security.userprincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret = "nguyenlong232253@gmail.com";
    private int jwtExpiration = 86400;
    public String createToken(Authentication authentication){
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername()).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime()+ jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public boolean validateToken(String token){
        try {
            //parseClaimsJws: (5 TH ngoại lệ)
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e){
            logger.error("Ivalid JWT signature ---> Message: {} ", e);
        } catch (MalformedJwtException e){
            logger.error("The token invalid format ---> Message: {}", e);
        } catch (UnsupportedJwtException e){
            logger.error("Unsupported JWT token ---> Message: {}", e);
        }catch (ExpiredJwtException e){
            logger.error("Expired JWT token ---> Message: {}", e);
        } catch (IllegalArgumentException e){
            logger.error("Jwt claims string is empty ---> Message: {}", e);
        }
        return false;
    }

    public String getUserNameFormToken(String token){
        String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName;
    }
}
