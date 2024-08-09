package com.example.demo.responses;

import com.example.demo.models.User;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private User user;
    private String status;
    private String message;

}
