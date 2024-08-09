package com.example.demo.services;


import com.example.demo.components.JwtTokenUtil;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.responses.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private  final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthResponse login(String username, String password) throws Exception {
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
        String accessToken= jwtTokenUtil.generateToken(user);
        String refreshToken=jwtTokenUtil.generateRefreshToken(new HashMap<>(),user);
        return new AuthResponse(accessToken, refreshToken, user, "success", "Đăng nhập thành công.");
    }

    public String refreshToken(String refreshToken) throws Exception {
        if (jwtTokenUtil.isTokenExpired(refreshToken)) {
            throw new RuntimeException("Refresh token đã hết hạn.");
        }
        // Trích xuất username từ refresh token
        String username = jwtTokenUtil.extractUsername(refreshToken);

        // Tìm người dùng từ username
        User user=userRepository.findByUsername(username).orElseThrow();

        // Tạo và trả về access token mới
        return jwtTokenUtil.generateToken(user);
    }
}
