//cette classe est le contreuleur de l'interface admin comporte les methodes suivantes :
package Controllers;

import Service.Service_user;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.RotateEvent;
import javafx.stage.Stage;


public class Admin1Controller implements Initializable {
 private Parent fxml;
     @FXML
    private JFXButton btt_favoris;

    @FXML
    private JFXButton btt_vosinformation;

    @FXML
    private JFXButton btt_deconnection;

    @FXML
    private JFXButton btt_accueil;

    @FXML
    private JFXButton btt_tags;

    @FXML
    private JFXButton btt_utilisateurs;

    @FXML
    private Text txt_welcome;

    @FXML
    private AnchorPane root;

    @FXML
    void Decconnection(MouseEvent event) { 
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("vous etes sur que tu vas deconnecter de votre compte ?");
            alert.showAndWait();
        ((Node)event.getSource()).getScene().getWindow().hide();
           Stage primaryStage = new Stage(); 
        Parent roott = null;
        try {
            roott = FXMLLoader.load(getClass().getResource("/interfaces/Login.fxml"));
        } catch (Exception e) {
                        e.printStackTrace();

        }

        Scene scene = new Scene(roott);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Administrateur ");
        primaryStage.show();
    }

    @FXML
    void afficher_favoris(MouseEvent event) {
          try {
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/user_favoris.fxml"));
              root.getChildren().removeAll();
              root.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();
          } 

    }

    @FXML
    void get_informations(MouseEvent event) {
         try {
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/Informations_personnelles.fxml"));
              root.getChildren().removeAll();
              root.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();
          } 
         

    }
        @FXML
    void users_list(MouseEvent event) {
        try {
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/Gestion_user.fxml"));
              root.getChildren().removeAll();
              root.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();
          } 
        

    }
        @FXML
    void allUserTags(MouseEvent event) {
         try {
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/Liste_tags.fxml"));
              root.getChildren().removeAll();
              root.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();
          } 
        

    }
     

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/Gestion_user.fxml"));
              root.getChildren().removeAll();
              root.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();
          } 
     
        
        txt_welcome.setText("Bienvenue "+Service_user.user.getNom()+" to TAGFILE");
    }    
    
}
