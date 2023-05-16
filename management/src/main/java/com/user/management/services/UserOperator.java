package com.user.management.services;

import com.user.management.model.User;

/**
 * @author Carine Iradukunda
 */
public interface UserOperator {
    Object getUsers();

    Object getUser(int id);

    Object createUser(User user);
}
