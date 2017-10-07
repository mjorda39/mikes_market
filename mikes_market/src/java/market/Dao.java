/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author mjord
 */
public class Dao {
    private Connection conn;
    private String db_pass;
    private String db_user;
    
    public Dao(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/marketDB"+ "?user=MikeyJordan&password=systor!");
        }
        catch (Exception e) {
        }
    }
    
    public User register(String username, String firstName, String lastName, String email, String password, String role){
        User user = new User();
        user.userName = username;
        user.firstName = firstName;
        user.lastName = lastName;
        user.email = email;
        user.password = password;
        user.role = role;
        try {
            Statement statement = conn.createStatement();
            int numRows = statement.executeUpdate("insert into marketDB.Users (username, firstname, lastname, email, pass, role, balance, lastTransaction) VALUES (" + "'" + username + "', '" + firstName + "', '" + lastName + "', '" + email + "', '" + password + "', '" + role + "', '" + user.balance + "', '" + user.transNumber + "');");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
    
    public User retrieveUser(String username){
        User user = null;
        try {
            Statement statement = conn.createStatement();
            ResultSet userData = statement.executeQuery("SELECT * FROM marketDB.Users WHERE username='" + username + "'");
            while(userData.next() != false){
                String db_user = userData.getString("username");
                String db_firstname = userData.getString("firstname");
                String db_lastname = userData.getString("lastname");
                String db_email = userData.getString("email");
                String db_pass = userData.getString("pass");   
                String db_role = userData.getString("role");
                user = setUser(db_user, db_firstname, db_lastname, db_email, db_pass, db_role);
                this.db_user = db_user;
                this.db_pass = db_pass;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
    
    public User setUser(String username, String firstName, String lastName, String email, String password, String role){
        User user = new User();
        user.userName = username;
        user.firstName = firstName;
        user.lastName = lastName;
        user.email = email;
        user.password = password;
        user.role = role;
        return user;
    }
    
    public void addToCart(String name, String quantity, String price){
        try {
            Statement statement = conn.createStatement();
            int items = statement.executeUpdate("INSERT INTO marketDB.cart (name, quantity, price) VALUES (" + "'" + name + "', '" + Integer.parseInt(quantity) + "', '" + Integer.parseInt(price)*Integer.parseInt(quantity) + "');");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public String ajaxCheckUser(String username){
        try {
            Statement statement = conn.createStatement();
            ResultSet users = statement.executeQuery("SELECT * FROM marketDB.Users WHERE username='" + username + "'");
            while(users.next() == true){
                return "<p style='color:red'>Username already in use.</p>";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "<p style='color:green'>Username available.</p>";
    }
    
    public boolean accountCheckUser(String username){
        boolean allowed = false;
        try {
            Statement statement = conn.createStatement();
            ResultSet users = statement.executeQuery("SELECT * FROM marketDB.Users WHERE username='" + username + "'");
            while(users.next() == true){
                return allowed;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        allowed = true;
        return allowed;
    }
    
    public UserBean createBean(String username){
        UserBean bean = new UserBean();
        try {
            Statement statement = conn.createStatement();
            ResultSet userData = statement.executeQuery("SELECT * FROM marketDB.Users WHERE username='" + username + "'");
            while(userData.next() != false){
                bean.setUsername(userData.getString("username"));
                bean.setFirstName(userData.getString("firstname"));
                bean.setLastName(userData.getString("lastname"));
                bean.setEmail(userData.getString("email"));
                bean.setPassword(userData.getString("pass"));
                bean.setRole(userData.getString("role"));
                bean.setBalance(Integer.parseInt(userData.getString("balance")));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }
    
    public String getUser(){
        return db_user;
    }
    
    public String getPass(){
        return db_pass;
    }
    
    public void closeDatabase(){
        try {
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
