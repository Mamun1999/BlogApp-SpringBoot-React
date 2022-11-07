package com.mamun.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
//process
     //1. get token
     //2. get user from token
     //3. validate token
     //4. load user associated wih token
     //5. set spring security 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       
             String requestToken=request.getHeader("Authorization");

             String username=null;

             String token= null;

             if(requestToken!=null && requestToken.startsWith("Bearer")){
                
               token= requestToken.substring(7);

               try {
                username= this.jwtTokenHelper.getUsernameFromToken(token);
               } catch (IllegalArgumentException ill) {
                  System.out.println("Unable to get jwt token");
               }
               catch(ExpiredJwtException exp){
                System.out.println("JWT has expired");
               }
               catch(MalformedJwtException Mal){
                System.out.println("Invalid JWT");
               }
             }
             else{
                System.out.println("Jwt does not start with Bearer");
             }

             //we got token that comes from a request
             //now validate the token

             if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

                //we can validate
               UserDetails userDetails= this.userDetailsService.loadUserByUsername(username);
                if(this.jwtTokenHelper.validateToken(token, userDetails)){
                    //do validate
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);// we have to provide authentication object so first declare authentication obj
                }
                else{
                    System.out.println("Jwt token is not valid");
                }
             }
             else{
                System.out.println("Username is null or context is not null");

             }

             //if authententication is set then doFilter will work and validation successful
             //if not then commence method will worok
             filterChain.doFilter(request, response);
        
    }


    
}
