package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main {

    static DataProvider  DP;
    
    static Properties property;

    public static void main(String[] args) {

        FileInputStream fis = null;
         property = new Properties();
        try {
            fis = new FileInputStream("src/javaapplication1/Server2/config.properties");
            property.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




         DP=new DataProvider();

        final Runner comandCatcher = new Runner(Integer.parseInt(property.getProperty("server.port")));
        // comandCatcher.Start();
        System.out.println("start Server");
        new Thread(comandCatcher).start();



     //   System.out.print("goodboy");

    }
}
