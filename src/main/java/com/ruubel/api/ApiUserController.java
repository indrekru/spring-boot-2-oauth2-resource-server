package com.ruubel.api;

import com.ruubel.model.User;
import com.ruubel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class ApiUserController {

    private UserService userService;

    @Autowired
    public ApiUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> userDetails() {

        User currentUser = userService.getCurrentUser();

        Map<String, Object> out = new HashMap<String, Object>() {{
            put("id", currentUser.getId());
            put("email", currentUser.getEmail());
            put("passwordHash", currentUser.getPassword());
        }};

        return new ResponseEntity<>(out, HttpStatus.OK);
    }

}
