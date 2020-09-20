package com.dangtuan.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dangtuan.common.dto.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Objects;
import org.apache.commons.codec.binary.Base64;

public class TokenHelper {

  public static UserDetails decode(final String token) throws IOException {
    final JWT jwt = new JWT();
    final DecodedJWT decodedJwt = jwt.decodeJwt(token);
    final ObjectMapper objectMapper = new ObjectMapper();

    final Base64 base64Url = new Base64(Boolean.TRUE);
    final String tokenJson = new String(base64Url.decode(decodedJwt.getPayload()));
    final UserDetails user = objectMapper.readValue(tokenJson, UserDetails.class);
    return user;
  }

  public static String getTokenForLogging(final String token) {
    final int startEndCharCount = 10;
    return Objects.nonNull(token) ? (token.length() > 2 * startEndCharCount) ?
        token.substring(0, startEndCharCount) + "...." + token
            .substring(token.length() - startEndCharCount)
        : token
        : "null";
  }
}
