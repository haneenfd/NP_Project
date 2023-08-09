/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NP_Project;

import java.rmi.Remote; 
import java.rmi.RemoteException;  
import java.sql.Connection;
import java.sql.SQLException;

// Creating Remote interface for our application 
public interface RMI extends Remote {  
   public Connection getConnection() throws RemoteException, SQLException; 
   public String delete(String id) throws RemoteException, SQLException;
   public String update(String id,String name, String gender,String dep, String age, String year) throws RemoteException, SQLException;
   public String insert(String id,String name, String gender,String dep, String age, String year) throws RemoteException, SQLException;

} 