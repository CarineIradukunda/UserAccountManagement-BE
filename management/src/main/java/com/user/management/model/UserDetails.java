package com.user.management.model;

import java.io.Serializable;

/**
 * @author Carine Iradukunda
 */
public record UserDetails(
      String lastName,
      String firstName,
      String gender,
      int age,
      String dob,
      String maritalStatus,
      String nationality,
      String nid
) implements Serializable {
}
