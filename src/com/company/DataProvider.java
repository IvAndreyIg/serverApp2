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
        /*FileInputStream fis = null;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/javaapplication1/Server2/config.properties");
            property.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        String host = Main.property.getProperty("db.host");
        String login = Main.property.getProperty("db.login");
        String password = Main.property.getProperty("db.password");


        try {
            conDB= DriverManager.getConnection("jdbc:mysql://"+host+"?useUnicode=true&serverTimezone=UTC",login,password);
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
    synchronized public void  newMessage(String sender,String receiver,String text){




// insert the data
        System.out.print("mess"+text);
        try {
            statement.executeUpdate("INSERT INTO Chat (`Sender`, `Reciever`, `MessageText`) VALUES ('"+sender+"', '"+receiver+"', '"+text+"')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //INSERT INTO `goose`.`chat` (`Sender`, `Reciever`, `MessageText`) VALUES ('ander', 'gigi', 'text');


    }
    synchronized public String  getChat(){

            //SELECT * FROM `Chat` 
String History="";
            ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * FROM Chat");
            
               //Sender Descending 1	Reciever	MessageText 	

            while ( rs.next() ) {
               // String lastName = rs.getString("User");
                 History += rs.getString("Sender")+":"+rs.getString("MessageText")+"]";
                
              //  System.out.println("name"+lastName);

            }
             


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return History;
// insert the data
      
        //INSERT INTO `goose`.`chat` (`Sender`, `Reciever`, `MessageText`) VALUES ('ander', 'gigi', 'text');


    }
}
