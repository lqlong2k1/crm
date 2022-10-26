package com.cybersoft.crm.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnection {
         private final static String urlDB = "jdbc:mysql://localhost:3307/crm_app";
        private final static String username="root";
        private final static String password="123";

        public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(urlDB,username,password);
        } catch (Exception e){
            System.out.println("Error connect database: "+e.getMessage());
        }
        return connection;
    }
}
