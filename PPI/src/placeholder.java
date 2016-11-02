
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ppi.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usman
 */
public class placeholder extends Thread{
    
    DBHandler db;
    public ArrayList<Node> node;
    Ppi pi;

    placeholder(ArrayList<Node> node1) {
         node = node1;
    }
  
    
    
    
    @Override
    public void run(){
     db=new DBHandler();
    node=db.isInDb(node);
        try {
            pi=new Ppi(node);
        } catch (SQLException ex) {
            Logger.getLogger(placeholder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public DBHandler getdb(){
        return this.db;
    }
     
    
}
