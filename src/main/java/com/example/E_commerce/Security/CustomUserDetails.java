package com.example.E_commerce.Security;

import com.example.E_commerce.Entity.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


//CustomUserDetails implements UserDetails to adapt a domain user entity into a format understood by Spring Security. It provides user credentials, roles, and account status used during authentication and authorization.
//CustomUserDetails is an adapter that converts your User entity into a format Spring Security understands.
//
//Spring Security does NOT know about your User entity.
//It only understands UserDetails.
//
//So this class acts as a bridge.
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
   public Collection<? extends GrantedAuthority>getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }//This define What roles/permissions the user has


    @Override
    public String getUsername() {
        return user.getEmail();
    }//this tells Spring Security:“This is the unique identifier for login” and you choose mail instead of username

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }//Spring Security uses this to:Compare password during login and Match with encoded password

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    //You returned true, meaning:
    //
    //Account is active
    //
    //Not locked
    //
    //Not expired
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
