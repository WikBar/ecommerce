package com.wiktor.ecommerce.Controller;

import com.wiktor.ecommerce.Model.User;
import com.wiktor.ecommerce.Repository.UserRepository;
import com.wiktor.ecommerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("")
    public String viewHomePage(){
        // return html page index from templates
        return "index";
    }
    @GetMapping("/register")
    public String viewRegisterPage(Model model){
        model.addAttribute("user", new User());
        return "register_form";
    }


    @GetMapping("/users")
    public String viewUsersPage(Model model){
        List<User> listUsers=userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }
    @GetMapping("/login")
    public String viewLoginPage(Model model){
        model.addAttribute("login", new User());
        return "login";
    }



    /*@PostMapping
    public ResponseEntity<String> addNewUser(@RequestBody User user){

        if(Objects.nonNull(userRepository.findByLogin(user.getLogin()))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Account exists");
        }
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Account created");
    }*/

}

