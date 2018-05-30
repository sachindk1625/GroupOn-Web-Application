/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.criteria.Order;

/**
 *
 * @author Sairam
 */
@Named(value = "userActions")
@ManagedBean
@SessionScoped
public class UserActions implements Serializable {

    /**
     * Creates a new instance of UserActions
     */
//    public UserActions() {
//    }
//    private String items="id"+"\t"+"item name"+"\t"+"category"+"\t"+"Alive Count"+"\t"+"seller id"+"<br></br>";
    private String items = "";
    private int size = 0;

    private String itemname;
    private String category;
    private int aliveCount;
    private String seller;
    private int itemid;
    private String customer;
    private int orderid;

    private int aliveCounter;

    private static ArrayList<OrderAct> order=new ArrayList<OrderAct>();
    private ArrayList<Item> item = new ArrayList<Item>();

    public ArrayList<Item> getItem() {
        return item;
    }

    public void setItem(ArrayList<Item> item) {
        this.item = item;
    }
    
    
    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAliveCount() {
        return aliveCount;
    }

    public void setAliveCount(int aliveCount) {
        this.aliveCount = aliveCount;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    //method for seller to view his items
    public String viewItems(String id) {
       items="";
       item.clear();
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            return ("internalError");
        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kales2422";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL,
                    "kales2422", "1541249");
            statement = connection.createStatement();

            resultSet = statement.executeQuery("Select * from item "
                    + "where seller = '"
                    + id + "'");

            while (resultSet.next()) {
//                System.out.println("Loop counter");
//                while(resultSet.next()){
//                return (resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getInt(4)+"\t"+resultSet.getString(5)+"<br></br>");
//                resultSet.next();
//                items = items + resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getInt(4) + "\t" + resultSet.getString(5) + "<br></br>";
//                }
                item.add(new Item(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5)));

            }
            return "";
//            return items;
           

        } catch (SQLException e) {
            e.printStackTrace();
            return ("internalError");
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //method for a seller to add new items
    public String addItems(String id) {
        seller = id;
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            return ("Internal Error! Please try again later.");
        }

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSet rsItem = null;

        try {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kales2422";

            connection = DriverManager.getConnection(DATABASE_URL,
                    "kales2422", "1541249");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select * from item "
                    + "where itemName = '"
                    + itemname + "'");

            if (resultSet.next()) {
                return ("this item is already present");
            } else {
                rsItem = statement.executeQuery("Select nextItem from newitem");
                if (rsItem.next()) {
                    itemid = rsItem.getInt(1);
                    int r = statement.executeUpdate("insert into item "
                            + "values ('" + itemid + "', '" + itemname + "', '"
                            + category + "','" + aliveCount + "','" + seller + "')");

                    int s = statement.executeUpdate("UPDATE `newitem` SET `nextItem`= '" + (itemid + 1) + "'");
                    return ("Item added successfully");
                } else {
                    return ("something went wrong.Try again after some time");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ("Internal Error! Please try again later.");

        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
                rsItem.close();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }

    //method to list out all items for a customer
    public String display() {
        item.clear();
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            return ("internalError");
        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kales2422";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL,
                    "kales2422", "1541249");
            statement = connection.createStatement();

            resultSet = statement.executeQuery("Select * from item");

            while (resultSet.next()) {
//                while(resultSet.next()){
//                return (resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getInt(4)+"\t"+resultSet.getString(5)+"<br></br>");
//                resultSet.next();
//                items = items + resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getInt(4) + "\t" + resultSet.getString(5) + "<br></br>";
//                }
                  item.add(new Item(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5)));
            }
            return "";
//            return items;
//            else{
//                return("you are not selling a single item till now");
//            }

        } catch (SQLException e) {
            e.printStackTrace();
            return ("internalError");
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //method for a user to buy somthing
    public String buy(String id) {
        customer = id;
        aliveCounter = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            return ("Internal Error! Please try again later.");
        }

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSet rsOrder = null;
        ResultSet rsSeller = null;
        ResultSet rsAlive = null;
        ResultSet rscheckPrior = null;

        try {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kales2422";

            connection = DriverManager.getConnection(DATABASE_URL,
                    "kales2422", "1541249");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select * from item "
                    + "where itemId = '"
                    + itemid + "'");

            if (!resultSet.next()) {
                return ("Sorry!!there is not item with that id");
            } else {
                rscheckPrior = statement.executeQuery("SELECT * FROM `order` WHERE customer = '" + customer + "' and item = '" + itemid + "'");
                if (!rscheckPrior.next()) {
                    rsOrder = statement.executeQuery("Select nextordernumber from nextorder");
                    if (rsOrder.next()) {
                        orderid = rsOrder.getInt(1);
                        rsSeller = statement.executeQuery("Select seller from item where itemId='" + itemid + "'");
                        if (rsSeller.next()) {
                            seller=rsSeller.getString(1);
                            rsAlive = statement.executeQuery("Select countAlive from item where itemId='" + itemid + "'");
                            if (rsAlive.next()) {
                                aliveCounter = rsAlive.getInt(1);
                                int updateKeepAlive = statement.executeUpdate("UPDATE `item` SET `countAlive`= '" + (aliveCounter - 1) + "'");
                            }
                            if (aliveCounter <= 1) {
                                int r = statement.executeUpdate("INSERT INTO `order` VALUES('" + orderid + "', '" + customer + "', '"+ seller + "','" + itemid + "','confirmed')");

                                int updateNextOrder = statement.executeUpdate("UPDATE `nextorder` SET `nextordernumber`= '" + (orderid + 1) + "'");
                                int updateAllOrders = statement.executeUpdate("UPDATE `order` SET `status`= 'confirmed' where item = '" + itemid + "'");
                                return ("Order successful");
                            } else {
                                int r = statement.executeUpdate("INSERT INTO `order` VALUES('" + orderid + "', '" + customer + "', '"
                                        + seller + "','" + itemid + "','pending')");

                                int updateNextOrder = statement.executeUpdate("UPDATE `nextorder` SET `nextordernumber`= '" + (orderid + 1) + "'");
                                return ("Order pending");
                            }
                        } else {
                            return ("Soryy!!this item is not sold anymore");
                        }
                    } else {
                        return ("something went wrong.Try again after some time");
                    }

                } else {
                    return ("you already bought this item");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return ("Internal Error! Please try again later.");

        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
                rsOrder.close();
                rsSeller.close();
                rsAlive.close();
                rscheckPrior.close();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }

    public static ArrayList<OrderAct> getOrder() {
        return order;
    }

    public static void setOrder(ArrayList<OrderAct> order) {
        UserActions.order = order;
    }
    
    //method for a customer to check his orders
    public String orderStatus(String id) {
//        items="";
        order.clear();
        customer = id;
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            return ("internalError");
        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/kales2422";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL,
                    "kales2422", "1541248");
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM `order` WHERE customer = '"+ customer + "'");

            while (resultSet.next()) {
//                while(resultSet.next()){
//                return (resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getInt(4)+"\t"+resultSet.getString(5)+"<br></br>");
//                resultSet.next();
                //items = items + resultSet.getInt(1) +"\t" + resultSet.getInt(4) + "\t" + resultSet.getString(5) + "<br></br>";
//                }
                order.add(new OrderAct(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5)));

            }
            return "";
//            return items;
//            else{
//                return("you are not selling a single item till now");
//            }

        } catch (SQLException e) {
            e.printStackTrace();
            return ("internalError");
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
