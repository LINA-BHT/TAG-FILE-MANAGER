/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class Change_mdpController implements Initializable {
private Parent fxml;
 @FXML
    private AnchorPane mdp_window;
        @FXML
    private JFXPasswordField pass_ancien;

    @FXML
    private JFXPasswordField pass_nouveaux;

    @FXML
    private JFXPasswordField pass_confirmation;


    @FXML
    private Button btt_confirmer;

    @FXML
    private Button btt_quitter;
     @FXML
    void confirmer(MouseEvent event) {
        String ancien_password = pass_ancien.getText();
        String nouv_password = pass_nouveaux.getText();
        String con_pass = pass_confirmation.getText();
        
        
        if(Service.Service_user.valid_password(ancien_password, Service.Service_user.user)){
            if(nouv_password.equals(con_pass)){
                Service.Service_user.Change_password(nouv_password, Service.Service_user.user.getId());
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("changement mot de passe effectuer avec succes ");
            alert.showAndWait();
            }
             else{
                pass_confirmation.setText("");
                  Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("confirmation du mot de passe incorrect");
            alert.showAndWait();
                
            }
           
                    
                    
        }
         else{
            pass_ancien.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("ancien mot de passe incorrecte !!!!!! resaisir le mot de passe ");
            alert.showAndWait();
                
            
        }

    }

    @FXML
    void quitter(MouseEvent event) {
        try {
            
             fxml= FXMLLoader.load(getClass().getResource("/interfaces/Informations_personnelles.fxml"));
              mdp_window.getChildren().removeAll();
              mdp_window.getChildren().setAll(fxml);
          } catch (IOException ex) {
              ex.printStackTrace();
          }

    }




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
