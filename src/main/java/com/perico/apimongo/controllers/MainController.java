package com.perico.apimongo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perico.apimongo.models.User;
import com.perico.apimongo.models.enums.UserRole;
import com.perico.apimongo.models.request.LoginRequest;
import com.perico.apimongo.models.request.RegisterRequest;
import com.perico.apimongo.repositories.UserService;
import com.perico.apimongo.settings.auth.JwtTokenTools;


@RestController
@CrossOrigin(origins = "*")
public class MainController {
    private final UserService userService;
    private final JwtTokenTools jwtTokenTools;

    public MainController(UserService userService, JwtTokenTools jwtTokenTools ) {
        this.userService = userService;
        this.jwtTokenTools = jwtTokenTools;
    }

    @PostMapping("/register")
    public ResponseEntity<String> postMethodName(@RequestBody RegisterRequest register) {
        User user =User.builder()
            .username(register.getUsername())
            .password(register.getPassword())
            .role(UserRole.USER)
            .build();
        
        user = userService.save(user);

        String token =jwtTokenTools.generateToken(user);

        
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByUsername(loginRequest.getUsername());

        // Error handling
        if(user == null){ 
            return ResponseEntity.badRequest().body("User not found");
        }

        if(!user.getPassword().equals(loginRequest.getPassword())){
            return ResponseEntity.badRequest().body("Wrong password");
        }

        String token =jwtTokenTools.generateToken(user);

        return ResponseEntity.ok(token);
        
    }

    @GetMapping("/filter")
    public ResponseEntity<String> filter(){
        return ResponseEntity.ok("Filter");
    }

    
}
