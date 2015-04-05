/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnection {
    
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/ios_schema";
    static String username = "root";
    static String password = "root";
    private static  Connection connection;

    public DBConnection() {
        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected successufully ....");
            }catch (Exception ex) {
			System.out.println("DBconnection exception 777 ");
            ex.printStackTrace();
        }

    }

    public static Connection getConnection() {
        System.out.println("get Connection methode ");
        return connection;
    }

   
}
