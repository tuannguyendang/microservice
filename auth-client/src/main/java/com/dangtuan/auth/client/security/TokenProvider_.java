//package com.dangtuan.auth.client.security;
//
//import com.dangtuan.auth.client.config.AppProperties;
//import io.jsonwebtoken.*;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//
//import java.security.KeyPair;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//@Log4j2
//public class TokenProvider_ {
//
//    private AppProperties appProperties;
//
//    public TokenProvider_(AppProperties appProperties) {
//        this.appProperties = appProperties;
//    }
//
//    @Autowired
//    private KeyPair keyPair;
//
//    public String createToken(Authentication authentication) {
//        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());
//        Map<String, Object> payloadClaims = new HashMap<>();
//        payloadClaims.put("authority", "[USER, VISITOR]");
//
//        return Jwts.builder()
//                .setClaims(payloadClaims)
//                .setSubject(Long.toString(userPrincipal.getId()))
//                .setIssuedAt(new Date())
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.RS256, keyPair.getPrivate())
//                .compact();
//    }
//
//    public Long getUserIdFromToken(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(keyPair.getPrivate())
//                .parseClaimsJws(token)
//                .getBody();
//
//        return Long.parseLong(claims.getSubject());
//    }
//
//    public boolean validateToken(String authToken) {
//        try {
//            Jwts.parser().setSigningKey(keyPair.getPrivate()).parseClaimsJws(authToken);
//            return true;
//        } catch (SignatureException ex) {
//            log.error("Invalid JWT signature");
//        } catch (MalformedJwtException ex) {
//            log.error("Invalid JWT token");
//        } catch (ExpiredJwtException ex) {
//            log.error("Expired JWT token");
//        } catch (UnsupportedJwtException ex) {
//            log.error("Unsupported JWT token");
//        } catch (IllegalArgumentException ex) {
//            log.error("JWT claims string is empty.");
//        }
//        return false;
//    }
//}