package com.user.management.services;

import com.user.management.model.User;
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

    @Autowired
    @Qualifier("userdb")
    DataSource source;

    @Override
    public Object getUsers(){
        var response = new User();
        Connection connection = null;
        try {
            connection = source.getConnection();
            String sql = "Select * from User";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                response.setLastName( resultSet.getString("LastName"));
                System.out.println(response.getLastName());
            }
        } catch (SQLException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        }

        return response;

    }


    @Override
    public Object getUser(int id){
        return null;
    }

    @Override
    public Object createUser(User user){

        return null;
    }
}
