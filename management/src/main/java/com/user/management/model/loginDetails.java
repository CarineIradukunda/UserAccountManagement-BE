package com.user.management.model;

import java.io.Serializable;

/**
 * @author Carine Iradukunda
 */
public record loginDetails(      String password,
                                 String email) implements Serializable {
}
