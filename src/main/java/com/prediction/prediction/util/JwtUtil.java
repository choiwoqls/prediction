package com.prediction.prediction.util;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;

public class JwtUtil {

     public static SecretKey getKeyFromSecret(String secret) {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(decodedKey);
    }

}
