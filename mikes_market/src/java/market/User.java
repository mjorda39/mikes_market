package market;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mjord
 */
public class User {
    public String userName = "";
    public String firstName = "";
    public String lastName = "";
    public String email = "";
    public String password = "";
    public String role = "";
    public double balance = 0;
    public int transNumber;
    public HashMap<String, Integer[]> cart = new HashMap<String, Integer[]>();
    
    public double addFunds(double inbal){
        balance += inbal;
        return balance;
    }
    
    public int calculateTotal(){
        int total = 0;
        for(String item : cart.keySet()){
            total += cart.get(item)[0]*cart.get(item)[1];
        }
        return total;
    }
}
