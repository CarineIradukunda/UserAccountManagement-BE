package com.user.management.services;

import com.user.management.model.User;
import com.user.management.model.UserDetails;
import com.user.management.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;

/**
 * @author Carine Iradukunda
 */

@Service
@Component
public class UserOperation implements UserOperator {

//    @Autowired
//    @Qualifier("userdb")
//    DataSource source;
//


    @Override
    public Object getUsers(){
        var response = new User();
        Connection connection = null;
        try {
            String jdbcUrl ="jdbc:sqlite:/E:\\USERSMGT\\BE\\UserAccountManagement-BE\\userdb.db";
            connection = DriverManager.getConnection(jdbcUrl);
            String sql = "Select * from User";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                response.setLastName( resultSet.getString("LastName"));
                System.out.println(response.getLastName());
            }
        } catch (SQLException e) {
            System.out.println("Error Connecting to the DB");
            throw new RuntimeException(e);
        }

        return response;

    }


    @Override
    public Object getUser(int id){
        return null;
    }

    @Override
    public Object createUser(UserDetails userDetails){
        var response = new UserResponse();
        Connection connection = null;
        try {
            String jdbcUrl ="jdbc:sqlite:/E:\\USERSMGT\\BE\\UserAccountManagement-BE\\userdb.db";
            connection = DriverManager.getConnection(jdbcUrl);
            String sql = "INSERT INTO User (LastName,FirstName,Gender,Age,DOB,MaritalStatus,Nationality,NID,AccountStatus,Creation_Time)" +
                    "values ('"+ userDetails.lastName()+"','"+ userDetails.firstName()+"','"+ userDetails.gender()+"','"+ userDetails.age()+"'," +
                    "'"+ userDetails.dob()+"','"+ userDetails.maritalStatus()+"','"+ userDetails.nationality()+"','"+ userDetails.nid()+"','UNVERIFIED',DATE())";
            Statement statement = connection.createStatement();
            int resultSet = statement.executeUpdate(sql);
            if (resultSet > 0) {
                System.out.println("ROW INSERTED");
            } else {
                System.out.println("ROW NOT INSERTED");
            }
        } catch (SQLException e) {
            System.out.println("Error Connecting to the DB");
            throw new RuntimeException(e);
        }

        return response;
    }
}
