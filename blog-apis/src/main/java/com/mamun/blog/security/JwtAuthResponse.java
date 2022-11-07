package com.mamun.blog.security;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class JwtAuthResponse {

    String token;
    
}
