package com.user.management.controller;

import com.user.management.model.UserDetails;
import com.user.management.services.UserOperator;
import org.springframework.web.bind.annotation.*;

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
    public Object fetchAllUsers(){
        return userOperator.getUsers();
    }

    @PostMapping("/signup")
    public Object insertUser(@RequestBody  UserDetails userDetails){
        return userOperator.createUser(userDetails);
    }
}
