package com.company;

import java.sql.*;

public class Main {

    static DataProvider  DP;

    public static void main(String[] args) {





         DP=new DataProvider();

        final Runner comandCatcher = new Runner(13);
        // comandCatcher.Start();
        System.out.println("start2");
        new Thread(comandCatcher).start();



     //   System.out.print("goodboy");

    }
}
