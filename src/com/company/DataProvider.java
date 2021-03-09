package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DataProvider {


    Connection conDB=null;
    Statement statement =null;


    public DataProvider() {
        FileInputStream fis = null;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/com/company/config.properties");
            property.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String host = property.getProperty("db.host");
        String login = property.getProperty("db.login");
        String password = property.getProperty("db.password");


        try {
            conDB= DriverManager.getConnection("jdbc:mysql://"+host+"?useUnicode=true&serverTimezone=UTC",login,"");
            statement=conDB.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        System.out.println("host:"+host);
    }

   synchronized public boolean  checkUser(String login,String pass){

        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * FROM UserPass WHERE User = '"+login+"'");



            if ( rs.next() ) {
               // String lastName = rs.getString("User");
                String password = rs.getString("Password");
                if (password.equals(pass))
                    return true;
                else
                    return false;
              //  System.out.println("name"+lastName);

            }
            else return false;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       /* */
        return false;

    }
}
