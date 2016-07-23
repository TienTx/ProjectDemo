/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tientx.supercode.myproejectdemov3.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zOzDarKzOz
 */
public class DatabaseConnection {

    private static DatabaseConnection instance = null;
    private Connection conn = null;

    private DatabaseConnection() {
        Properties properties = new Properties();
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbPJ?user=root&password=&characterEncoding=UTF-8");
            properties.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("properties/dbconfig.properties"));
            Class.forName(properties.getProperty("DRIVER_CLASSNAME"));
            conn = DriverManager.getConnection(properties.getProperty("CONNECT_STRING"));
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

//    public static void main(String[] args) {
//        System.out.println(DatabaseConnection.getInstance().getConnection().toString());
//    }

}
