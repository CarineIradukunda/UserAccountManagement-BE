package com.user.management.model;


public class User {

  public String Names;
  public String Email;
//  public String gender;
//  public long age;
//  public String dob;
//  public String maritalStatus;
//  public String nationality;
  public String nid;
  public String accountStatus;
//  public String photo;
//  public java.sql.Timestamp creationTime;

  public User(String Names, String Email, String accountStatus,String nid) {
    this.Names =Names;
    this.Email = Email;
    this.accountStatus = accountStatus;
    this.nid = nid;
  }
}
