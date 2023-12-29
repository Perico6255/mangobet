package com.perico.apimongo.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegisterRequest {
    private String username;
    private String password;
    
}
