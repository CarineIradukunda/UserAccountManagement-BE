package com.user.management.services;

import com.user.management.model.User;
import com.user.management.model.UserDetails;

/**
 * @author Carine Iradukunda
 */
public interface UserOperator {
    Object getUsers();

    Object getUser(int id);

    Object createUser(UserDetails userDetails);
}
