package com.example.back.controller;

import com.example.back.entity.User;
import com.example.back.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 유저 경로에 Post인 경우는 회원가입
    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        User user = userService.createUser(username, password, email);
        return ResponseEntity.ok(user);
    }

    // 유저 경로 id 넣어서 delete인 경우는 탈퇴
    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@Parameter(description = "ID of the user to be deleted", required = true) @PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
