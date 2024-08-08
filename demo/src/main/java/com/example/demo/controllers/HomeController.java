package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class HomeController {
    private  final UserService userService;

    @GetMapping("")
    public String index(){
        return "Home page";
    }

    @GetMapping("/layout")
    public String layout(){
        return "Layout page";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin page";
    }
    @GetMapping("/admin/1")
    public String admin1(){
        return "admin page";
    }
    @DeleteMapping("/admin/meo")
    public String admin2(){
        return "admin page";
    }
    @PostMapping("/admin/nai")
    public String admin3(){
        return "admin page";
    }

    @GetMapping("/test4/user")
    public String index1(){
        return "User test4 page";
    }

    @GetMapping("test4/admin")
    public String index2(){
        return "Admin test4 page";
    }

    @GetMapping("test4/both")
    public String index3(){
        return "USER ADMIn page";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
             @RequestBody User user) {
        // Kiểm tra thông tin đăng nhập và sinh token
        try {
            String token = userService.login(user.getUsername(), user.getPassword());
            // Trả về token trong response
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
