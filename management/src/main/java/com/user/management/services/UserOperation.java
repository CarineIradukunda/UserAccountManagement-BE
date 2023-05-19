package com.user.management.services;

import com.user.management.model.User;
import com.user.management.model.UserDetails;
import com.user.management.model.UserResponse;
import com.user.management.util.AppLogger;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Carine Iradukunda
 */

@Service
@Component
public class UserOperation implements UserOperator {

    final Environment environment;

    public UserOperation(Environment environment) {
        this.environment = environment;
    }


    @Override
    public List<User> getUsers(){
        List<User> subs = new ArrayList<>();
        Connection connection = null;
        try {
            String jdbcUrl = environment.getProperty("connection");
            connection = DriverManager.getConnection(jdbcUrl);
            String sql = "Select * from User";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                subs.add(new User(resultSet.getString(1),
                        resultSet.getString(12),
                        resultSet.getString(8),
                        resultSet.getString(7)));
            }

            return subs;

        } catch (SQLException e) {
            AppLogger.LOGGER.error("Error While Fetching users" +  e.getMessage());
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                AppLogger.LOGGER.error("SQL server exception While Fetching users " + e.getMessage());
            }
        }


    }


    @Override
    public Object getUser(UserDetails userDetails){
        var response = new UserResponse();
        Connection connection = null;
        try {
            String jdbcUrl = environment.getProperty("connection");
            connection = DriverManager.getConnection(jdbcUrl);
            String responseQuery = "SELECT * FROM User WHERE NID = '" + userDetails.nid().trim() + "'";
            Statement statement1 = connection.createStatement();
            ResultSet resultSet1 = statement1.executeQuery(responseQuery);
            while (resultSet1.next()) {
                response.setNames(resultSet1.getString("Names"));
                response.setEmail(resultSet1.getString("Email"));
                response.setNid(resultSet1.getString("NID"));
                response.setMessage("Success");
                response.setStatusCode(1000);

            }
            throw new RuntimeException();
        }catch (SQLException e) {
                AppLogger.LOGGER.error("Error While  Getting one user" + e.getMessage());
                response.setMessage(e.getMessage());
                response.setStatusCode(1004);
                throw new RuntimeException(e);

            }
        finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    AppLogger.LOGGER.error("SQL server exception While Getting one user" + e.getMessage());
                }
            }


    }

    @Override
    public Object createUser(UserDetails userDetails){
        var response = new UserResponse();
        Connection connection = null;
        var pwd = userDetails.password();
        var nid = userDetails.nid().trim();
        var encryptedPwd= BCrypt.hashpw(pwd,BCrypt.gensalt());
        try {
            String jdbcUrl = environment.getProperty("connection");
            connection = DriverManager.getConnection(jdbcUrl);
            String sql = "INSERT INTO User (Names,Gender,Age,DOB,MaritalStatus,Nationality,NID,AccountStatus,photo,Creation_Time,Password,Email)values ('"+ userDetails.names()+"','"+ userDetails.gender()+"','"+ userDetails.age()+"','"+ userDetails.dob()+"','"+ userDetails.maritalStatus()+"','"+userDetails.nationality()+"','"+ nid+"','UNVERIFIED','',datetime('now'),'"+encryptedPwd+"','"+userDetails.email().trim()+"')";
            Statement statement = connection.createStatement();
            int resultSet = statement.executeUpdate(sql);
            if (resultSet > 0) {
                String responseQuery = "SELECT * FROM User WHERE NID = '"+userDetails.nid().trim()+"'";
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
            AppLogger.LOGGER.error("Error While Signing up "+e.getMessage());
            response.setMessage("Failed");
            response.setStatusCode(1004);

        }
        finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                AppLogger.LOGGER.error("SQL server exception While Signing up " + e.getMessage());
            }
        }

        return response;
    }


    @Override
    public Object login(UserDetails userDetails){
        var response = new UserResponse();
        Connection connection = null;
        var pwd = userDetails.password().trim();
        var encryptedPwd ="";

        try {
            String jdbcUrl = environment.getProperty("connection");
            connection = DriverManager.getConnection(jdbcUrl);
            String responseQuery = "SELECT * FROM User WHERE NID = '"+ userDetails.nid().trim()+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(responseQuery);
            while (resultSet.next()){
                encryptedPwd = resultSet.getString("Password");
            }
            if(BCrypt.checkpw(pwd,encryptedPwd)){
                String responseQuery2 = "SELECT * FROM User WHERE NID = '" + userDetails.nid().trim()+ "'";
                Statement statement1 = connection.createStatement();
                ResultSet resultSet1 = statement1.executeQuery(responseQuery2);
                while (resultSet1.next()) {
                    response.setNames(resultSet1.getString("Names"));
                    response.setEmail(resultSet1.getString("Email"));
                    response.setMessage("Success");
                    response.setStatusCode(1000);
                }
            }
            else {
                response.setMessage("Password Incorrect");
                response.setStatusCode(1005);
            }

        } catch (SQLException e) {
            AppLogger.LOGGER.error("Error While Logging in" + e.getMessage());
            response.setMessage(e.getMessage());
            response.setStatusCode(1004);
            throw new RuntimeException(e);

        }
        finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                AppLogger.LOGGER.error("SQL server exception While Logging in" + e.getMessage());
            }
        }

        return response;
    }


    @Override
    public Object requestVerify(UserDetails userDetails){
        var response = new UserResponse();
        Connection connection = null;
        try {
            String jdbcUrl = environment.getProperty("connection");
            connection = DriverManager.getConnection(jdbcUrl);
            String sql = "UPDATE User set AccountStatus = 'PENDING VERIFICATION' where NID = '"+ userDetails.nid() +"'";
            Statement statement = connection.createStatement();
            int resultSet = statement.executeUpdate(sql);
            if (resultSet > 0) {
                String responseQuery = "SELECT * FROM User WHERE NID = '" + userDetails.nid().trim()+ "'";
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
            AppLogger.LOGGER.error("Error While A user requests verification"+ e.getMessage());
            response.setMessage(e.getMessage());
            response.setStatusCode(1004);
            throw new RuntimeException(e);

        }
        finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                AppLogger.LOGGER.error("SQL server exception while A user requests verification " + e.getMessage());
            }
        }

        return response;
    }



    @Override
    public Object verifyUser(UserDetails userDetails){
        var response = new UserResponse();
        Connection connection = null;
        try {
            String jdbcUrl = environment.getProperty("connection");
            connection = DriverManager.getConnection(jdbcUrl);
            String sql = "UPDATE User set AccountStatus = 'VERIFIED' where  NID = '"+ userDetails.nid() +"' And AccountStatus = 'PENDING VERIFICATION'";
            Statement statement = connection.createStatement();
            int resultSet = statement.executeUpdate(sql);
            if (resultSet > 0) {
//SEND EMAIL ON VERIFICATION
                //CALL EMAIL SERVICE
                String responseQuery = "SELECT * FROM User WHERE NID = '" + userDetails.nid().trim()+ "'";
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
            AppLogger.LOGGER.error("Error While Verifying the user" + e.getMessage());
            response.setMessage(e.getMessage());
            response.setStatusCode(1004);
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                AppLogger.LOGGER.error("SQL server exception Verifying the user" + e.getMessage());
            }
        }

        return response;
    }

}
