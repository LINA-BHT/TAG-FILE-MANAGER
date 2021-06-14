/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Service.Service_user;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.IOException;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import jdk.nashorn.internal.runtime.options.Option;
import models.file;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * FXML Controller class
 *
 * @author linah
 */
public class User_favorisController implements Initializable {
   

  private Parent fxml;
      @FXML
    private AnchorPane favoris_window;

     @FXML
    private JFXTextField txt_searchTag;

    @FXML
    private JFXTextField txt_searchAuteur;

    @FXML
    private JFXTextField txt_searchTitre;
    @FXML
    private TableView<file> table_fichiers;

    @FXML
    private TableColumn<file, String> col_nom;

    @FXML
    private TableColumn<file, String> col_titre;

    @FXML
    private TableColumn<file, String> col_emplacement;

    @FXML
    private Button byy_Ajouter;

    @FXML
    private Button btt_modifier;

    @FXML
    private Button btt_supprimer;

 

    @FXML
    void supprimer_fichier(ActionEvent event) {
     if(table_fichiers.getSelectionModel().getSelectedItem() != null)
        {
        int id=  table_fichiers.getSelectionModel().getSelectedItem().getId();
        System.out.println(""+id+"");
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Delete file");
            alert.setHeaderText("vous etes sure que tu vas suppimer ce fichiers ??");
            alert.setContentText(""+table_fichiers.getSelectionModel().getSelectedItem().getTitre()+"");
           Optional<ButtonType> option = alert.showAndWait();
           if(option.get()==ButtonType.OK){
               Service_user.supprimer_fichier(id);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete file");
                alert.setHeaderText(null);
            alert.setContentText("file deleted");
            alert.showAndWait();
            try {
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/user_favoris.fxml"));
              favoris_window.getChildren().removeAll();
              favoris_window.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();
          }
           
               
               
           }
           else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Delete file");
                alert2.setHeaderText(null);
            alert2.setContentText("canclled");
           alert2.showAndWait();
               
           }
        }
     else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("FILE NOT SELECTED");
                alert.setHeaderText(null);
            alert.setContentText("sellectionner un fichier de votre liste favoris pour le supprimer");
           alert.showAndWait();
         
     }
        
     
        

    }
    @FXML
    void modifier_fichier(ActionEvent event) {
         if(table_fichiers.getSelectionModel().getSelectedItem() != null)
        {
         int  id=  table_fichiers.getSelectionModel().getSelectedItem().getId();
         Service_user.selectedFile_data(id);
            
            try {
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/Modifier_fichier.fxml"));
              favoris_window.getChildren().removeAll();
              favoris_window.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();
          }
      
        }
     else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("FILE NOT SELECTED");
                alert.setHeaderText(null);
            alert.setContentText("sellectionner un fichier de votre liste favoris pour le modifier");
           alert.showAndWait();
         }

    }

    @FXML
    void new_fichier(ActionEvent event) {
        try {
            
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/Ajouter_fichier.fxml"));
              favoris_window.getChildren().removeAll();
              favoris_window.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();
          }

    }
    @FXML
    void affiche_fichierliste(ActionEvent event) {
        try {
            File f = new File("liste fichier.txt");
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
       @FXML
                // ouvrir le fichier selectionner en utilisant son chemin
    void ouvrir_fichier(ActionEvent event) {
         
                  try {
                    String path =  table_fichiers.getSelectionModel().getSelectedItem().getEmplacement();
                      System.out.println(table_fichiers.getSelectionModel().getSelectedItem().getType());
                      String fichier ="fichier";
                      String page_web="page web";
                    if(table_fichiers.getSelectionModel().getSelectedItem().getType().equals(fichier)){
                    File selectedfile=new File(path);
                    Desktop desktop= Desktop.getDesktop();
           
                           desktop.open(selectedfile);
                            System.out.println(table_fichiers.getSelectionModel().getSelectedItem().getType());
                    }
                    else if(table_fichiers.getSelectionModel().getSelectedItem().getType().equals(page_web)) {
                        Desktop.getDesktop().browse(new URI(path));
                    }
           
           
          } catch (IOException ex) {
              ex.printStackTrace();
          } catch (URISyntaxException ex) {
                Logger.getLogger(User_favorisController.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
        

    }
    

    ObservableList<file> listM;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        col_titre.setCellValueFactory(new PropertyValueFactory<file,String>("titre"));
        col_emplacement.setCellValueFactory(new PropertyValueFactory<file,String>("emplacement"));
        col_nom.setCellValueFactory(new PropertyValueFactory<file,String>("nom_fichier"));
        //recupere observablelist<file> qui contient les informations de tous les fichiers
        listM = Service_user.getfiledata();
    ArrayList<file> files=Service_user.getfiles();
    
    
       
        
   // 
        PrintWriter writer;
    try {
        File f=new File("liste fichier.txt");
 writer = new PrintWriter(f, "UTF-8");
       for (file file1 : files) {
           writer.println(""+file1.toString()+"");
           
              System.out.println("finished");
            
              
          }
       writer.close();
    
     } catch (Exception e) {
       e.printStackTrace();
     }
        
         /*Cree un objet FilteredList<file> pour filtrer la liste des fichiers
        selon le texte ecrit dans la recherche */
        FilteredList<file> filtereData= new FilteredList(listM,p-> true);
     
        txt_searchTag.textProperty().addListener((observable, oldValue, newValue) -> {
	filtereData.setPredicate(file  -> {
       
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
                                       
				}
                                
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                                 
				
				if (file.getList_tags().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
                                }
				     else  
				    	 return false; // Does not match.
			});
		});
        //filter selon l'auteur de fichier
         txt_searchAuteur.textProperty().addListener((observable, oldValue, newValue) -> {
	filtereData.setPredicate(file  -> {
       
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
                                       
				}
                                
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                                 
				
				if (file.getAuteur().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
                                }
				     else  
				    	 return false; // Does not match.
			});
		});
         // filter selon le titre de fichier
            txt_searchTitre.textProperty().addListener((observable, oldValue, newValue) -> {
	filtereData.setPredicate(file  -> {
       
				// If filter text is empty, display all file
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
                                       
				}
                                
				
				// Compare titre  de chaque fichiers avec le texte de txt_searchTitre
				String lowerCaseFilter = newValue.toLowerCase();
                                 
				
				if (file.getTitre().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
                                }
				     else  
				    	 return false; // Does not match.
			});
		});
   
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<file> sortedData = new SortedList<>(filtereData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table_fichiers.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
	     table_fichiers.setItems(sortedData);
             
             
            
    } 
    
    
}
