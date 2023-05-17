package com.user.management.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

  private String Names;
  private String Email;
  private String gender;
  private long age;
  private String dob;
  private String maritalStatus;
  private String nationality;
  private String nid;
  private String accountStatus;
  private String photo;
  private java.sql.Timestamp creationTime;
}
