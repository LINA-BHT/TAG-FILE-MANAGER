/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;




import models.user;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.file;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;




/**
 *
 * @author linah
 */
public class Service_user {
    public static user user =new user();
    public static user selecteduser = new user();
    public static file selectedFile=new file();
    
    public static void ajout(user nouveaux_utilisateur){
      Connection c = DbConnection.getConnect();
      PreparedStatement preparedStatement;
       try {


            preparedStatement = c.prepareStatement("INSERT INTO USERS ( LOGIN,MDP,NOM,PRENOM,EMAIL,STATUT) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, nouveaux_utilisateur.getLogin());
            preparedStatement.setString(2, nouveaux_utilisateur.getMdp());
            preparedStatement.setString(3,nouveaux_utilisateur.getNom());
            preparedStatement.setString(4, nouveaux_utilisateur.getPrenom());
             preparedStatement.setString(5,nouveaux_utilisateur.getEmail());
            preparedStatement.setString(6, "SIMPLE USER");
            preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        
        
    }
    public static ObservableList<user> getusersData (){
        Connection conn = DbConnection.getConnect();
        ObservableList<user> list = FXCollections.observableArrayList();
             try {
                 PreparedStatement ps = conn.prepareStatement("select * from users where statut<>'admin' ");
                 ResultSet rs = ps.executeQuery();
                 while(rs.next())
                 {
                     list.add(new user(Integer.parseInt(rs.getString("id")) , rs.getString("nom"), rs.getString("prenom"), rs.getString("login"),rs.getString("mdp"), rs.getString("email")));
                 }


       

             } catch (SQLException ex) {
                  System.out.println(ex.getMessage());
               }
      
        return list ;
    }
     public static int count_users() {
       
          Connection c = DbConnection.getConnect();
            Statement cmd;
            ResultSet res;
            int nb =0;
            
       
        try {
           
            
            cmd = c.createStatement();
            res = cmd.executeQuery("select * from users ");
            while(res.next())
            {
                nb++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Service_user.class.getName()).log(Level.SEVERE, null, ex);
        }
         return (nb-1) ;
     }
     public static void Change_password(String new_password,int id_user){
      Connection c = DbConnection.getConnect();
      PreparedStatement preparedStatement;
       try {
            preparedStatement = c.prepareStatement("UPDATE USERS SET MDP = '"+new_password+"' where id="+id_user+"");
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     }
      public static void Change_information(user user){
          
           Connection c = DbConnection.getConnect();
             PreparedStatement preparedStatement;
            String nom = user.getNom();
            String prenom = user.getPrenom();
            String login =user.getLogin();
            String email=user.getEmail();
            int  id =user.getId();

       
        try {
           
            
            preparedStatement = c.prepareStatement("UPDATE USERS SET nom = '"+nom+"' , email = '"+email+"' ,prenom = '"+prenom+"' ,login = '"+login+"'  where id="+id+"");
          preparedStatement.executeUpdate();
            
 
        } catch (SQLException ex) {
            Logger.getLogger(Service_user.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
public static boolean login(String Login_user, String mdp_user){
     Connection c = DbConnection.getConnect();
     boolean test =false;
       try{        
       Statement cmd = c.createStatement();
       ResultSet res;
            String NOM=Login_user;
            String mdp=mdp_user;
         res = cmd.executeQuery("SELECT * FROM USERS WHERE LOGIN='"+ NOM +"' AND MDP='"+mdp+"'" );
       if (res .next())
       {
           user.setId(res.getInt("id"));
           user.setNom(res.getString("nom"));
           user.setPrenom(res.getString("prenom"));
           user.setLogin(res.getString("login"));
           user.setEmail(res.getString("email"));
           user.setMdp(res.getString("mdp"));
           user.setStatus(res.getString("statut"));
           test=true;
       
           
    }
       else 
       {
           test=false;
          
       }
       

       }catch(SQLException esql){
           System.out.println(esql.getMessage());
       }
       return test;
    }
public static boolean user_exist(String Login_user){
     Connection c = DbConnection.getConnect();
     boolean test =false;
       try{        
       Statement cmd = c.createStatement();
       ResultSet res;
            String NOM=Login_user;
            
         res = cmd.executeQuery("SELECT * FROM USERS WHERE LOGIN='"+ NOM +"'" );
       if (res .next())
       {
         
           test=true;
       
           
    }
       else 
       {
           test=false;
          
       }
       

       }catch(SQLException esql){
           System.out.println(esql.getMessage());
       }
       return test;
    }
public static boolean valid_password(String ancien_mdp, user user){
     Connection c = DbConnection.getConnect();
     boolean test =false;
       try{        
       Statement cmd = c.createStatement();
       ResultSet res;
            String mdp1=ancien_mdp;
          int id_user=  user.getId();
          
         res = cmd.executeQuery("SELECT * FROM USERS WHERE id="+ id_user+"");
     
       if (res.next())
       {
           if(res.getString("mdp").equals(mdp1)){
                test=true;
           }
          
         
       else 
       {
           test=false;
          
       }
       }
       
       

       }catch(SQLException esql){
           System.out.println(esql.getMessage());
       }
       return test;
    }
public static void selecteduser_data(int id_selecteduser){
     Connection c = DbConnection.getConnect();
     
       try{        
       Statement cmd = c.createStatement();
       ResultSet res;
           int id = id_selecteduser;
         res = cmd.executeQuery("SELECT * FROM USERS WHERE ID="+ id +"");
       if (res .next())
       {
           selecteduser.setId(res.getInt("id"));
           selecteduser.setNom(res.getString("nom"));
           selecteduser.setPrenom(res.getString("prenom"));
           selecteduser.setLogin(res.getString("login"));
           selecteduser.setEmail(res.getString("email"));
           selecteduser.setStatus(res.getString("statut"));
           
       
           
    }
       }catch(SQLException esql){
           System.out.println(esql.getMessage());
       }
}
public static void ajout_fichier(file nouveaux_fichier , int id_user){
      Connection c = DbConnection.getConnect();
      PreparedStatement preparedStatement;
       try {


            preparedStatement = c.prepareStatement("INSERT INTO FICHIERS ( ID_USER,CHEMIN,AUTEUR,TITRE,TAGS,RESUMER,COMMENTAIRE,NOM,TYPE) VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,id_user);
            preparedStatement.setString(2, nouveaux_fichier.getEmplacement());
            preparedStatement.setString(3,nouveaux_fichier.getAuteur());
            preparedStatement.setString(4, nouveaux_fichier.getTitre());
             preparedStatement.setString(5,nouveaux_fichier.getTags().toString());
            preparedStatement.setString(6,nouveaux_fichier.getResumer());
            preparedStatement.setString(7,nouveaux_fichier.getCommentaire());
            preparedStatement.setString(8,nouveaux_fichier.getNom_fichier());
            preparedStatement.setString(9,nouveaux_fichier.getType());
            
            preparedStatement.execute();
            System.out.println(""+nouveaux_fichier.getEmplacement()+"");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
    public static ObservableList<file> getfiledata (){
        Connection conn = DbConnection.getConnect();
        ObservableList<file> list = FXCollections.observableArrayList();
        int id_user= user.getId();
             try {
                 PreparedStatement ps = conn.prepareStatement("select * from FICHIERS where ID_USER="+id_user+" ");
                 ResultSet rs = ps.executeQuery();
                 while(rs.next())
                 {
                     list.add(new file(rs.getInt("ID_FICHIER"),rs.getString("titre"), rs.getString("auteur"), rs.getString("resumer"), rs.getString("commentaire"), rs.getString("nom"), rs.getString("CHEMIN"), rs.getString("TAGS"),rs.getString("TYPE")));
                     
                 }


       

             } catch (SQLException ex) {
                  System.out.println(ex.getMessage());
               }
             
      
        return list ;
    }
     public static ArrayList<file> getfiles (){
        Connection conn = DbConnection.getConnect();
        List<file> list = new ArrayList<file>();
        int id_user= user.getId();
             try {
                 PreparedStatement ps = conn.prepareStatement("select * from FICHIERS where ID_USER="+id_user+" ");
                 ResultSet rs = ps.executeQuery();
                 while(rs.next())
                 {
                     file ficgier =new file(rs.getInt("ID_FICHIER"),rs.getString("titre"), rs.getString("auteur"), rs.getString("resumer"), rs.getString("commentaire"), rs.getString("nom"), rs.getString("CHEMIN"), rs.getString("TAGS"));
                     list.add(ficgier);
                     
                 }
                
             } catch (SQLException ex) {
                  System.out.println(ex.getMessage());
               }
             
      
        return (ArrayList<file>) list ;
    }
    public static void supprimer_fichier(int id_fichier){
      Connection c = DbConnection.getConnect();
      PreparedStatement preparedStatement;
       try {


            preparedStatement = c.prepareStatement("DELETE FROM FICHIERS WHERE ID_FICHIER = "+id_fichier+"");
      
             preparedStatement.execute();
          

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
    public static List<String> gettags_data(int id_user)
    {
        Connection c = DbConnection.getConnect();
        List<String> List = null ;
        ArrayList<String> myList =new ArrayList<String>(Arrays.asList()) ;
        
     
       try{        
       Statement cmd = c.createStatement();
       ResultSet res;
       String tag;
        String replace;
        
  
         res = cmd.executeQuery("SELECT * FROM FICHIERS WHERE ID_USER="+ id_user+"");
       while (res .next())
       {
           tag= res.getString("tags");
          replace = tag.replace("[","");
      System.out.println(replace);
       String replace1 = replace.replace("]","");
       System.out.println(replace1);
       List = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
       myList.addAll(List);
       System.out.println(myList.toString());
           
       }
           } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
      
 return myList;
 
}
     public static int count_fichiers_user(int id_user) {
       
          Connection c = DbConnection.getConnect();
            Statement cmd;
            ResultSet res;
            int nb =0;
            
       
        try {
           
            
            cmd = c.createStatement();
            res = cmd.executeQuery("select * from FICHIERS WHERE ID_USER="+ id_user+" ");
            while(res.next())
            {
                nb++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Service_user.class.getName()).log(Level.SEVERE, null, ex);
        }
         return (nb) ;
     }
      public static ArrayList<String> getALLtags_data()
    {
        Connection c = DbConnection.getConnect();
        ArrayList<String> myList =new ArrayList<String>(Arrays.asList()) ;
        
        
     
       try{        
       Statement cmd = c.createStatement();
       ResultSet res;
       String tag;
        String replace;
        
  
         res = cmd.executeQuery("SELECT * FROM FICHIERS ");
       while(res .next())
       {
           
           tag= res.getString("tags");
           replace = tag.replace("[","");
           System.out.println(replace);
           String replace1 = replace.replace("]", "");
           System.out.println(replace1);
           List<String> List = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
           myList.addAll(List);
       
      System.out.println(myList);
      
       }
           } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
      
 return myList;
 
}
       public static int count_tag_user(String tag ) {
       
          Connection c = DbConnection.getConnect();
            Statement cmd;
            ResultSet res;
            int nb =0;
            
       
        try {
           
            
            cmd = c.createStatement();
            res = cmd.executeQuery("select * from FICHIERS WHERE TAGS LIKE '%"+tag+"%' ");
            
            while(res.next())
            {
                System.out.println(res.getString("TAGS"));
                nb++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Service_user.class.getName()).log(Level.SEVERE, null, ex);
        }
         return (nb) ;
     }
        public static ObservableList<user> get_users_oftag(String tag ) {
          ObservableList<user> list = FXCollections.observableArrayList();
       
          Connection c = DbConnection.getConnect();
          
            
       
        try {
             PreparedStatement ps = c.prepareStatement("select * from users where ID in (select ID_USER from FICHIERS WHERE TAGS LIKE '%"+tag+"%') ");
                 ResultSet rs = ps.executeQuery();
           
       
            
             while(rs.next())
                 {
                     list.add(new user(Integer.parseInt(rs.getString("id")) , rs.getString("nom"), rs.getString("prenom"), rs.getString("login"),rs.getString("mdp"), rs.getString("email")));
                 }
                
            

        } catch (SQLException ex) {
            Logger.getLogger(Service_user.class.getName()).log(Level.SEVERE, null, ex);
        }
         
          
      return list;
      
    
}


public static void selectedFile_data(int id_fichier){
     Connection c = DbConnection.getConnect();
     
       try{        
       Statement cmd = c.createStatement();
       ResultSet res;
           int id = id_fichier;
         res = cmd.executeQuery("SELECT * FROM FICHIERS WHERE ID_FICHIER="+ id +"");
       if (res .next())
       {
           selectedFile.setId(res.getInt("ID_FICHIER"));
           selectedFile.setAuteur(res.getString("AUTEUR"));
           selectedFile.setTitre(res.getString("TITRE"));
           selectedFile.setCommentaire(res.getString("COMMENTAIRE"));
           selectedFile.setList_tags(res.getString("TAGS"));
           
           selectedFile.setResumer(res.getString("RESUMER"));
          
           
       
           
    }
       }catch(SQLException esql){
           System.out.println(esql.getMessage());
       }
}
 public static void modifierFile(file file){
          
           Connection c = DbConnection.getConnect();
             PreparedStatement preparedStatement;
            String titre = file.getTitre();
            String tags = file.getList_tags();
            String auteur = file.getAuteur();
            String resumer = file.getResumer();
            String commentaire = file.getCommentaire();
            int id=file.getId();
            
       
        try {
           
            
            preparedStatement = c.prepareStatement("UPDATE FICHIERS SET AUTEUR = '"+auteur+"' ,TITRE = '"+titre+"' , TAGS = '"+tags+"' ,RESUMER = '"+resumer+"' ,COMMENTAIRE = '"+commentaire+"'  where ID_FICHIER="+id+"");
          preparedStatement.executeUpdate();
            
 
        } catch (SQLException ex) {
            Logger.getLogger(Service_user.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
 
 public static boolean mail_exist(String mail){
     Connection c = DbConnection.getConnect();
     boolean test =false;
       try{        
       Statement cmd = c.createStatement();
       ResultSet res;
            String email=mail;
            
         res = cmd.executeQuery("SELECT * FROM USERS WHERE EMAIL='"+ mail +"'" );
       if (res .next())
       {
         
           test=true;
       
           
    }
       else 
       {
           test=false;
          
       }
       

       }catch(SQLException esql){
           System.out.println(esql.getMessage());
       }
       return test;
    }

  public static String get_password(String mail){
     Connection c = DbConnection.getConnect();
     String pass = null;
     
       try{        
       Statement cmd = c.createStatement();
       ResultSet res;
            String email=mail;
            
         res = cmd.executeQuery("SELECT * FROM USERS WHERE EMAIL='"+ mail +"'" );
       if (res .next())
       {
           
         
          pass= res.getString("MDP");
       
           
    }
      

       }catch(SQLException esql){
           System.out.println(esql.getMessage());
       }
       return pass;
    }

}  



