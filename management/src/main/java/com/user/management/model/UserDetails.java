package com.user.management.model;

import java.io.Serializable;

/**
 * @author Carine Iradukunda
 */
public record UserDetails(
      String names,
      String firstName,
      String gender,
      int age,
      String dob,
      String maritalStatus,
      String nationality,
      Integer nid,
      String password,
      String email
) implements Serializable {
}
