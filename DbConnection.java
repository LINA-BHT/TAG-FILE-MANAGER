/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.*;

/**
 *
 * @author linah
 */
public class DbConnection {
    private static String URL_BD = "jdbc:oracle:thin:@localhost:1521:xe";
    private static   String Util = "GESTION DOSSIER";
    private static   String MP = "lina";
    private static   Connection c;
     
   
     public static Connection getConnect (){
         try{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch(ClassNotFoundException ex ){
            System.out.println(ex.getMessage());
            
        }
        
         try{
             
             c = DriverManager.getConnection(URL_BD,Util,MP);
             
         }catch(SQLException esql){
           System.out.println(esql.getMessage());
       }
         return c;
        
     }  
     
       
    
}
