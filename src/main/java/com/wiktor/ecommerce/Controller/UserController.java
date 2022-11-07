package com.wiktor.ecommerce.Controller;

import com.wiktor.ecommerce.Model.User;
import com.wiktor.ecommerce.Repository.UserRepository;
import com.wiktor.ecommerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/register")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> addNewUser(@RequestBody User user){

        if(Objects.nonNull(userRepository.findByLogin(user.getLogin()))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Account exists");
        }
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Account created");
    }

}
