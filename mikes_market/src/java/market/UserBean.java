/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market;

/**
 *
 * @author mjord
 */
public class UserBean implements java.io.Serializable {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private double balance;
    
    public UserBean(){
        username = "";
        firstName = "";
        lastName = "";
        email = "";
        password = "";
        role = "";
        balance = 0;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public void setFirstName(String firstname){
        this.firstName = firstname;
    }
    
    public String getFirstName(){
        return this.firstName;
    }
    
    public void setLastName(String lastname){
        this.lastName = lastname;
    }
    
    public String getLastName(){
        return this.lastName;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setRole(String role){
        this.role = role;
    }
    
    public String getRole(){
        return this.role;
    }
    
    public void setBalance(int balance){
        this.balance = balance;
    }
    
    public double getBalance(){
        return this.balance;
    }
    
    @Override
    public String toString(){
        return this.getUsername() + " " + this.getFirstName() + " " + this.getLastName() +
                " " + this.getEmail() + " " + this.getPassword() + " " + this.getRole() +
                " " + this.getBalance();
    }
}
