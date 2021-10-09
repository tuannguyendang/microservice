package com.dangtuan.auth.client.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dangtuan.auth.client.config.AppProperties;
import com.dangtuan.auth.client.model.AuthProvider;
import com.dangtuan.auth.client.payload.LoginRequest;
import com.dangtuan.auth.client.util.constants.ApiConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

@Service
@Log4j2
public class TokenProvider {

    private AppProperties appProperties;

    @Autowired
    private KeyPair keyPair;

    @Autowired
    private OAuth2AuthorizedClientManager authorizedClientManager;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        return JWT.create().withArrayClaim(ApiConstants.AUTHORITY, new String[]{"USER", "VISITOR"}).withSubject(Long.toString(userPrincipal.getId()))
                .withIssuedAt(now).withExpiresAt(expiryDate).withIssuer(ApiConstants.ISSUER)
                .sign(Algorithm.RSA512((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate())).toString();
    }

    public String createMyAuthToken(Authentication authentication, LoginRequest loginRequest) {
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId(AuthProvider.myoauth.name())
                .principal(authentication)
                .attributes(attrs -> {
                    attrs.put(LoginRequest.class.getName(), loginRequest);
//                    attrs.put(HttpServletResponse.class.getName(), servletResponse);
                })
                .build();
        OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        authorizedClient.getRefreshToken();
        return accessToken.getTokenValue();
    }

    public Long getUserIdFromToken(String token) {
        return Long.parseLong(JWT.decode(token).getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Algorithm algorithm = Algorithm.RSA512((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate());
            JWTVerifier verifier = JWT.require(algorithm).acceptLeeway(1).withIssuer(ApiConstants.ISSUER)
                    .build();
            verifier.verify(authToken);
            return true;
        } catch (JWTVerificationException ex) {
            log.error(ex.getStackTrace());
        }
        return false;
    }

}
