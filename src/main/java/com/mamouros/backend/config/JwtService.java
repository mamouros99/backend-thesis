package com.mamouros.backend.config;

import com.mamouros.backend.auth.User.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private PrivateKey privateKey;
    private PublicKey publicKey;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;

    public void generateKeys() {
        try{
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair kp = keyPairGenerator.genKeyPair();
            privateKey = kp.getPrivate();
            publicKey = kp.getPublic();
        }
        catch (NoSuchAlgorithmException e){
            System.out.println("NO keys generated: " + e.getMessage());
        }

    }
    private Claims extractAllClaims(String jwtToken){
        return Jwts
                .parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(jwtToken)
                .getBody();

    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public String generateToken(User userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String jwtToken, UserDetails userDetails){
        final String username = extractUsername(jwtToken);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken){
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    public String generateToken(Map<String, Object> extractClaims, User userDetails){

        if(publicKey == null || privateKey == null){
            generateKeys();
        }

        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.RS512, privateKey)
                .compact();

    }




}
