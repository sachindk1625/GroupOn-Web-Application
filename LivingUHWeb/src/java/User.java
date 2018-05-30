/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import javax.inject.Named;
//import javax.enterprise.context.Dependent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


/**
 *
 * @author Sairam
 */
@ManagedBean
@RequestScoped
public class User {

    /**
     * Creates a new instance of User
     */
    private String id;
    private String password;
    private boolean isSeller;
    private int seller;
//    public User() {
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsSeller() {
        return isSeller;
    }

    public void setIsSeller(boolean isSeller) {
        this.isSeller = isSeller;
    }
    
    public String login(){       
     return ("heellloo");   
    }
    
    public String register(){
        if(isSeller==true){
            seller =1;
        }
        else{
            seller = 0;
        }
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            return ("Internal Error! Please try again later.");
        }
         
         
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        
        try
        {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kales2422";
            
            
            connection = DriverManager.getConnection(DATABASE_URL, 
                    "kales2422", "1541249");   
            statement = connection.createStatement();
            
            resultSet = statement.executeQuery("Select * from user "
                    + "where username = '" + 
                    id + "'" );
            
            if(resultSet.next())
            {
                 return("You are registered already.Or try with a different user id");
            }
            else
            {
                int r = statement.executeUpdate("insert into user "
                        + "values ('" + id + "', '" + password + "', '" 
                    + seller + "')");
                return ("Registration Successful! Please "
                         + "return to login your account.");
                
            }   
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return ("Internal Error! Please try again later.");
             
        }
        finally
        {
            try
            {
                resultSet.close();
                statement.close();
                connection.close();
                
            }
            catch (Exception e)
            {
                 
                e.printStackTrace();
            }
        }
    }
    
}
