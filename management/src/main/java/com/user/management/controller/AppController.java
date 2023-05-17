package com.user.management.controller;

import com.user.management.model.User;
import com.user.management.model.UserDetails;
import com.user.management.services.UserOperator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Carine Iradukunda
 */

@RestController
@CrossOrigin
@RequestMapping("user/v1")
public class AppController {

    final UserOperator userOperator;


    public AppController(UserOperator userOperator) {
        this.userOperator = userOperator;
    }

    @GetMapping("/all/users")
    public List<User> fetchAllUsers(){
        return userOperator.getUsers();
    }

    @PostMapping("/signup")
    public Object insertUser(@RequestBody  UserDetails userDetails){
        return userOperator.createUser(userDetails);
    }

    @PostMapping("/login")
    public Object checkUser(@RequestBody  UserDetails userDetails){
        return userOperator.login(userDetails);
    }
}
