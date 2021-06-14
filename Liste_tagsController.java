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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;
import models.TAG;
import models.user;

public class Liste_tagsController implements Initializable {
    private Parent fxml;
    public static String selected_tag;
    public static int  nbuser_selected_tag;

    
     @FXML
    private AnchorPane tags_window;

    @FXML
    private JFXTextField txt_chercher;

    @FXML
    private TableView<TAG> tab_utilisateurs;

    @FXML
    private TableColumn<TAG, String> col_tag;

    @FXML
    private TableColumn<TAG, Integer> col_nbuser;

    @FXML
    private Label lbl_nbtags;

    @FXML
    private Button btt_fichiertags;

    @FXML
    void afficher_tagstxt(MouseEvent event) {
         try {
            File f = new File("liste tagss.txt");
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

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int nb_tags;
        
         col_tag.setCellValueFactory(new PropertyValueFactory<TAG,String>("nom"));
        col_nbuser.setCellValueFactory(new PropertyValueFactory<TAG,Integer>("nb_users"));
         ObservableList<TAG> list_TagsNbUsers = FXCollections.observableArrayList();
         ArrayList<String> tags = Service.Service_user.getALLtags_data();
       //effacer les elements qui se repete dans la liste tag 
       for(int i=0;i<tags.size();i++){
        for(int j=i+1;j<tags.size();j++){
            if(tags.get(i).equals(tags.get(j))){
                tags.remove(j);
                j--;
            }
        }
    }
       
    
                             
                         
      
        for (String tag : tags) {
             nb_tags=Service.Service_user.count_tag_user(tag);
             
             list_TagsNbUsers.add(new TAG(tag, nb_tags));
             
            
        }
        
        
        
          //lister  les utilisateurs dans un fichier texte
           PrintWriter writer;
    try {
        File f=new File("liste tagss.txt");
 writer = new PrintWriter(f, "UTF-8");
       for (String tag : tags) {
           writer.println(""+tag+"\n");
           
              System.out.println("finished");
            
              
          }
       writer.close();
    
     } catch (Exception e) {
       e.printStackTrace();
     }
       
          FilteredList<TAG> filtereData= new FilteredList(list_TagsNbUsers,p-> true);
     
        txt_chercher.textProperty().addListener((observable, oldValue, newValue) -> {
	filtereData.setPredicate(TAG  -> {
       
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
                                       
				}
                                
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                                 
				
				if (TAG.getNom().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
                                }
				     else  
				    	 return false; // Does not match.
			});
		});
       // 3. Wrap the FilteredList in a SortedList. 
		SortedList<TAG> sortedData = new SortedList<>(filtereData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tab_utilisateurs.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
	     tab_utilisateurs.setItems(sortedData);
             tab_utilisateurs.setOnMouseClicked((MouseEvent event) -> {
                  try {
                    selected_tag=  tab_utilisateurs.getSelectionModel().getSelectedItem().getNom();
                    nbuser_selected_tag=tab_utilisateurs.getSelectionModel().getSelectedItem().getNb_users();
                    
            
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/liste_users_oftag.fxml"));
              tags_window.getChildren().removeAll();
              tags_window.getChildren().setAll(fxml);
             
          } catch (IOException ex) {
              ex.printStackTrace();
          }
         
        
        });
             lbl_nbtags.setText(""+tags.size()+"");
             
          
      
       
    }      
    
}

