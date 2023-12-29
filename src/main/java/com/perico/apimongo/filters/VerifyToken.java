package com.perico.apimongo.filters;

import java.io.IOException;

import com.perico.apimongo.models.enums.UserRole;
import com.perico.apimongo.settings.auth.JwtTokenTools;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class VerifyToken implements Filter {
    private final JwtTokenTools jwtTokenTools;

    public VerifyToken(JwtTokenTools jwtTokenTools) {
        this.jwtTokenTools = jwtTokenTools;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Get request and response 
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // get token from header
        String token = httpRequest.getHeader("Authorization");

        if(!validateToken(token)){
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean validateToken(String token) {
        if (token != null ) {
            return jwtTokenTools.validateToken(token, UserRole.USER);
        }
        return false;
    }
}
