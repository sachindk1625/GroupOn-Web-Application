/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import java.io.Serializable;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Sairam
 */
@Named(value = "userLogin")
@ManagedBean
@SessionScoped
public class UserLogin implements Serializable {

    /**
     * Creates a new instance of UserLogin
     */
//    public UserLogin() {
//    }
    private String id;
    private String password;
    
    //get methods and set methods
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    public String login()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ("internalError");
        }
        
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kales2422";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        
        try
        {      
            connection = DriverManager.getConnection(DATABASE_URL, 
                    "kales2422", "15412349");   
            statement = connection.createStatement();
            
            resultSet = statement.executeQuery("Select * from user "
                    + "where username = '" + 
                    id + "'" );
            
            if(resultSet.next())
            {
                if(password.equals(resultSet.getString(2)))
                {
                    if(resultSet.getBoolean(3)==true){
                        return "sellerAccount";
                    }
                    else{
                        return "studentAccount";
                    }
                    
                     
                }
                else
                {
                    id = "";
                    password = "";
                    return "loginError";    
                }
            }
            else
            {
                id = "";
                password = "";
                return "loginError";
                 
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return ("internalError");
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
    public String signOut()
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml";       
    }
    
}
