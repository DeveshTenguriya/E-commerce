package com.example.E_commerce.Config;

import com.example.E_commerce.Security.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
//JwtFilter intercepts every HTTP request, extracts and validates the JWT token, loads the user, and sets authentication in Spring Security’s context.
//Because it extends OncePerRequestFilter:
//
//✅ It runs ONCE for EVERY HTTP request
//Examples:
//
///login
//
///api/orders
//
///api/cart
//
///api/admin
//
//(Spring Security decides which requests actually require authentication.)
//JwtFilter is a Spring Security filter that executes once per request, extracts and validates the JWT token, loads user details, and populates the SecurityContext to enable authorization.
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtServices jwtServices;
    private final UserDetailServiceImpl userDetailService;

    public JwtAuthenticationFilter(JwtServices jwtServices, UserDetailServiceImpl userDetailService) {
        this.jwtServices = jwtServices;
        this.userDetailService = userDetailService;
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authHeader= request.getHeader("Authorization");

        String token = null;
        String username= null;

        //extract token and username from previously generated token during login
        if (authHeader!=null && authHeader.startsWith("Bearer ")) {
            token= authHeader.substring(7);
            username= jwtServices.extractUsername(token);
        }

        //Check if authentication already exists : (SecurityContextHolder.getContext().getAuthentication()== null)
        if (username!=null && SecurityContextHolder.getContext().getAuthentication()== null){

            //the user was called by its name as the validation of token required username not email
            UserDetails userDetails=
                    userDetailService.loadUserByUsername(username);

            if (jwtServices.validateToken(token,userDetails)){
                //Create Authentication object
                //This tells Spring:
                //User is authenticated
                //What roles they have
                UsernamePasswordAuthenticationToken authToken=
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                //Attach request details and add ip address
                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                //Set authentication in SecurityContext
                //After this:
                //@PreAuthorize
                //@Secured
                //hasRole()
                //ALL work.
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);
            }
        }

        //Moves request forward.
        filterChain.doFilter(request,response);

    }
}
