package com.user.management.services;

import com.user.management.model.User;
import com.user.management.model.UserDetails;
import com.user.management.model.UserResponse;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
        var pwd = userDetails.password();
        var encryptedPwd= BCrypt.hashpw(pwd,BCrypt.gensalt());
        try {
            String jdbcUrl ="jdbc:sqlite:/E:\\USERSMGT\\BE\\UserAccountManagement-BE\\userdb.db";
            connection = DriverManager.getConnection(jdbcUrl);
            String sql = "INSERT INTO User (Names,Gender,Age,DOB,MaritalStatus,Nationality,NID,AccountStatus,Creation_Time,Password,Email)" +
                    "values ('"+ userDetails.names()+"','"+ userDetails.gender()+"','"+ userDetails.age()+"'," +
                    "'"+ userDetails.dob()+"','"+ userDetails.maritalStatus()+"','"+ userDetails.nationality()+"','"+ userDetails.nid()+"','UNVERIFIED',DATE(),'"+encryptedPwd+"','"+userDetails.email()+"')";
            Statement statement = connection.createStatement();
            int resultSet = statement.executeUpdate(sql);
            if (resultSet > 0) {
                String responseQuery = "SELECT * FROM User WHERE EMAIL = '"+userDetails.email()+"'";
                Statement statement1 = connection.createStatement();
                ResultSet resultSet1 = statement1.executeQuery(responseQuery);
                while (resultSet1.next()){
                    response.setNames(resultSet1.getString("Names"));
                    response.setEmail(resultSet1.getString("Email"));
                    response.setMessage("Success");
                    response.setStatusCode(1000);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error Connecting to the DB");
            response.setMessage("Failed");
            response.setStatusCode(1004);
            throw new RuntimeException(e);
        }

        return response;
    }
}
