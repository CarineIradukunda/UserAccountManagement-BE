package com.user.management.services;

import com.user.management.model.User;
import com.user.management.model.UserDetails;

import java.util.List;

/**
 * @author Carine Iradukunda
 */
public interface UserOperator {
    List<User> getUsers();

    Object getUser(int id);

    Object createUser(UserDetails userDetails);

    Object login(UserDetails userDetails);

    Object requestVerify(UserDetails userDetails);

    Object verifyUser(UserDetails userDetails);
}
