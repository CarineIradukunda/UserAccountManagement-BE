package com.user.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.sql.*;

//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
//		try {
//			Connection connection = null;
//			String jdbcUrl ="jdbc:sqlite:/E:\\USERSMGT\\BE\\UserAccountManagement-BE\\userdb.db";
//			connection = DriverManager.getConnection(jdbcUrl);
//			String sql = "Select * from User";
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(sql);
//			while (resultSet.next()){
//				String v = ( resultSet.getString("LastName"));
//				System.out.println("++++++++++"+v);
//			}
//		} catch (SQLException e) {
//			System.out.println("Error Connecting to the DB");
//			throw new RuntimeException(e);
//		}
	}

}
