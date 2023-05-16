package com.user.management.controller;

import com.user.management.services.UserOperator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
