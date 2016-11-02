/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ppi;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.*;

/**
 *
 * @author student
 */
public class DB {
    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

    Statement           statement;
    ResultSet           resultSet;
   Connection          connection;
   int i=0;
    public DB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://localhost:3306/db" ;
       connection= DriverManager.getConnection(connectionUrl,"ppi_gelsoft","root"); 
              statement=connection.createStatement();

            System.out.println("connected");
        } catch (SQLException e) {
            System.out.println("SQL Exception: "+ e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Class Not Found Exception: "+ cE.toString());
        }
    }
    public void executeEntry(String entry) throws SQLException
{
    if (connection == null || statement == null) {
            System.err.println("There is no database to execute the query.");
            return;
        }
        try {
         statement.execute(entry);
         }
         catch(MySQLIntegrityConstraintViolationException  sqe)
         {
             i=1;
         sqe.printStackTrace();
         System.out.println("DB operation Exception");
         }
        catch(Exception e){
            
         e.printStackTrace();
         System.out.println("DB operation Exception");
        }

}
     public ResultSet execute(String entry)
{
    if (connection == null || statement == null) {
            System.err.println("There is no database to execute the query.");
            return null;
        }
        try {
          resultSet=statement.executeQuery(entry);
         }catch(SQLException sqe){
         System.out.println("Execute  DB RS Open Exception"+sqe.getMessage());
         }
         return resultSet;
}
    public void close()
     {
     try{
         if(statement!=null)
         {
     statement.close();
         }
         if(resultSet!=null)
         {
     resultSet.close();
         }
         if(connection!=null)
         {
     connection.close();
         }
     }
     catch(SQLException s)
     {
     System.out.println("Not close");
     }
     }

 public ResultSet insert(String entry)
{
    if (connection == null || statement == null) {
            System.err.println("There is no database to execute the query.");
            return null;
        }
        try {
          resultSet=statement.executeQuery(entry);
         }catch(SQLException sqe){}
         return resultSet;
}

}

