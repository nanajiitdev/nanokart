package com.user.login.security;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    // Use a secure key with at least 32 bytes
    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey123";

    private static final Key KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String generateToken(String email) {
          return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )
                .signWith(KEY)
                .compact();
    }

    public static String extractEmail(String token) {

        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public static boolean validate(String token) {

        try {

            Jwts.parser()
                    .verifyWith((javax.crypto.SecretKey) KEY)
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }

    }

}
