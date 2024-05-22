package com.example.cleansmile;
import android.widget.Toast;

import java.sql.*;
public class db {
    public void dataBase(){
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "java";
        String password = "password";

        Toast toast = Toast.makeText(db.this, "Connecting database ...", Toast.LENGTH_LONG).show();

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
