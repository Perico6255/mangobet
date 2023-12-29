package com.perico.apimongo.settings.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.perico.apimongo.models.User;
import com.perico.apimongo.models.enums.UserRole;


@Service
public class JwtTokenTools {

    @Value("${jwt.userPassword}")
    private String userPassword;
    @Value("${jwt.adminPassword}")
    private String adminPassword;
    

    public String generateToken(User user){


        try {
            String secret = userPassword;

            if(user.getRole().equals(UserRole.ADMIN)){
                secret = adminPassword;
            }

            Algorithm algorithm = Algorithm.HMAC256(secret);

            Object token = JWT.create()
                    .withIssuer("perico api")
                    .withSubject(user.getStringId())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiration time
                    .sign(algorithm);
            return token.toString();

        } catch (JWTCreationException e){
            e.printStackTrace();
        }
        return null;
    }


    public Boolean validateToken(String token, UserRole userRole){
        try {

            String secret = userPassword;

            if(userRole.equals(UserRole.ADMIN)){
                secret = adminPassword;
            }

            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();



            verifier.verify(token);

            return true;

        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }
        return false;
    }
}
