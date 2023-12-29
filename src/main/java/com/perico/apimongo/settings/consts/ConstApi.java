package com.perico.apimongo.settings.consts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@NoArgsConstructor
public class ConstApi {
    @Value("${jwt.userPassword}")
    private String userPassword;

    @Value("${jwt.adminPassword}")
    private String adminPassword;

    
}
