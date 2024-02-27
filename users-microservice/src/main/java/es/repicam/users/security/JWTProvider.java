package es.repicam.users.security;

import es.repicam.users.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTProvider {

    @Value("${jwt.secret")
    private String secret;

    @PostConstruct
    protected void init() {
        this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims = Jwts.claims().subject(user.getUsername()).build();
        claims.put("id", user.getId());
        Date now = new Date();
        Date exp = new Date(now.getTime() + 3600000);

        return Jwts.builder().
                claims(claims).
                issuedAt(now).
                expiration(exp).
                signWith(SignatureAlgorithm.HS256, secret).
                compact();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public boolean isValid(String token){
        try {
            getSubjectFromToken(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            return getSubjectFromToken(token);
        } catch (Exception e){
            return "Bad token provided";
        }
    }

    private String getSubjectFromToken(String token) {
        return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }
}
