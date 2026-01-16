package com.example.E_commerce.Security;

import com.example.E_commerce.Entity.User;
import com.example.E_commerce.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//UserDetailsService loads a user from the database and converts it into a UserDetails object that Spring Security uses for authentication and authorization.
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    //What this loaduser function does

    //Receives email (from login or token)
    //
    //Queries database
    //
    //Throws exception if not found
    //
    //Wraps entity into CustomUserDetails
    //
    //Returns it to Spring Security
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user= userRepository.findByEmail(email)
                .orElseThrow(()->
                        new UsernameNotFoundException("user not found"));

        return new CustomUserDetails(user);
    }
}
