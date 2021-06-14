/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Service.Service_user;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.user;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.file;


public class Liste_users_oftagController implements Initializable {
    private Parent fxml;
      @FXML
    private AnchorPane listetags_window;

    @FXML
    private Label lb_titre;

    @FXML
    private JFXTextField txt_chercher;

    @FXML
    private TableView<user> tab_utilisateurs;

    @FXML
    private TableColumn<user, Integer> col_id;

    @FXML
    private TableColumn<user, String> col_nom;

    @FXML
    private TableColumn<user, String> col_prenom;

    @FXML
    private TableColumn<user, String> col_login;

    @FXML
    private TableColumn<user, String> col_email;

    @FXML
    private Label nb_utilisateur;
    @FXML
    void listage_users(ActionEvent event) {
    try {
            File f = new File("liste userss.txt");
            BufferedReader br = null;
            FileReader fr = null;
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String sCurrentLine ;
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }
            
       
     
       
        
                   
                    Desktop desktop= Desktop.getDesktop();
           
                   desktop.open(f);
           
           
         } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ObservableList<user> listM;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         nb_utilisateur.setText(""+ Liste_tagsController.nbuser_selected_tag+"");//Nombre utilisateurs inscrit
        
        //
        col_id.setCellValueFactory(new PropertyValueFactory<user,Integer>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<user,String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<user,String>("prenom"));
        col_login.setCellValueFactory(new PropertyValueFactory<user,String>("login"));
        col_email.setCellValueFactory(new PropertyValueFactory<user,String>("email"));
        System.out.println("--------------------------------------");
        System.out.println(""+Liste_tagsController.selected_tag+"");
        listM = Service_user.get_users_oftag(""+Liste_tagsController.selected_tag+""); 
        //lister  les utilisateurs dans un fichier texte
           PrintWriter writer;
    try {
        File f=new File("liste userss.txt");
 writer = new PrintWriter(f, "UTF-8");
       for (user user11 : listM) {
           writer.println(""+user11.toString()+"");
           
              System.out.println("finished");
            
              
          }
       writer.close();
    
     } catch (Exception e) {
       e.printStackTrace();
     }
       
        /*Cree un objet FilteredList<user> pour filtrer la liste des users 
        selon le texte ecrit dans la recherche */
        FilteredList<user> filtereData= new FilteredList(listM,p-> true);
     
        txt_chercher.textProperty().addListener((observable, oldValue, newValue) -> {
	filtereData.setPredicate(user  -> {
       
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (user.getLogin().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (user.getEmail().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(user.getPrenom()).contains(lowerCaseFilter))
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<user> sortedData = new SortedList<>(filtereData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tab_utilisateurs.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
	     tab_utilisateurs.setItems(sortedData);
             tab_utilisateurs.setOnMouseClicked((MouseEvent event) -> {
                  try {
                    int id=  tab_utilisateurs.getSelectionModel().getSelectedItem().getId();
                    Service_user.selecteduser_data(id);
            
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/Detailles_user.fxml"));
              listetags_window.getChildren().removeAll();
              
              listetags_window.getChildren().setAll(fxml);
             
          } catch (IOException ex) {
              ex.printStackTrace();
          }
                 
        });
    }    
    
}
