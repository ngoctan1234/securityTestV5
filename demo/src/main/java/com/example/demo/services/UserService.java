package com.example.demo.services;


import com.example.demo.components.JwtTokenUtil;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private  final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public String login(String username, String password) throws Exception {
//        Optional<User> optionalUser = userRepository.findByUsername(username);
//        if(optionalUser.isEmpty()) {
//            throw new DataNotFoundException("Invalid phone number / password");
//        }
//        //return optionalUser.get();//muốn trả JWT token ?
//        User existingUser = optionalUser.get();
//        //check password

        User user=userRepository.findByUsername(username).orElseThrow();

            if(!passwordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("Wrong phone number or password");
            }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username, password,
                user.getAuthorities()
        );

        //authenticate with Java Spring security
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(user);
    }
}
