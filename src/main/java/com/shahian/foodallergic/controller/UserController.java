package com.shahian.foodallergic.controller;

import com.shahian.foodallergic.model.entity.Food;
import com.shahian.foodallergic.model.entity.User;
import com.shahian.foodallergic.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/v1/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
    @GetMapping("/v1/user")
    public ResponseEntity<?> getUserById(@RequestParam Long userId) {
         User   user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/v1/checkSensitivity")
    public ResponseEntity<?> checkSensitivityFood(@RequestParam Long userId) {

        List<String> sensitiveList = userService.checkSensitivityFood(userId);
        List<Food> substitutes = userService.getSubstitutes(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("sensitiveList", sensitiveList);
        response.put("suggestedSubstitutes", substitutes);
        return ResponseEntity.ok(response);

    }

}