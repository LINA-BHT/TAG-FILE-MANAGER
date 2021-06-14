package Controllers;

import Service.Service_user;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;








public class LoginController implements Initializable {
    @FXML
    private JFXPasswordField txt_mdp ; 
     @FXML
    private JFXTextField txt_nom;
     @FXML
     private Label lb_mdp;
    
     
     
   
    @FXML
    private void login(ActionEvent event ){//connecter l'utilisateur a son espace 
     
            String login=txt_nom.getText();
            String mdp=txt_mdp.getText();
            boolean test=Service_user.login(login, mdp);
       //login valid
       if (test)
       {
           //redirection user
           System.out.println(""+Service_user.user.getStatus()+"");
           if (Service_user.user.getStatus().equals("SIMPLE USER")){
               
           
               ((Node)event.getSource()).getScene().getWindow().hide();
           Stage primaryStage = new Stage(); 
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/interfaces/Simple_user.fxml"));
        } catch (Exception e) {
        }

        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TADFILE");
        primaryStage.show();
           }
           //redirection admin
           else {
              ((Node)event.getSource()).getScene().getWindow().hide();
           Stage primaryStage = new Stage(); 
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/interfaces/Admin1.fxml"));
        } catch (Exception e) {
        }

        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ADMINISTRATEUR");
        primaryStage.show();
           }     
    }
       //login invalide
       //user existe invalid password
       else if (Service_user.user_exist(login))
       {
           
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("heloo "+login+"");
            alert.setContentText("Vote mot de passe et incorrecte!! si tu est oublier ton mot de passe taper sur mot de passe oublier");
            alert.showAndWait();
            txt_mdp.setText("");
       }
       //user do not existe
       else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("user inexiste ou login incorrecte");
            alert.setContentText("cree un compte ou rersaisir le login");
            alert.showAndWait();
            txt_nom.setText("");
            txt_mdp.setText("");
           
       }
       
    }
          

    @FXML
    private void new_utilisateur(ActionEvent event){// cree un nouveaux utilisateur 
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage(); 
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/interfaces/creation_user.fxml"));
        } catch (Exception e) {
                        e.printStackTrace();

        }

        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("cree un compte TAGFILE");
        primaryStage.show();

    }
        @FXML
    void recuperer_mdp(MouseEvent event) {
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Recuperer mot e passe ");
            alert.setHeaderText(null);
            alert.setContentText("tu veut qu'on envoi votre mot de passe par mail ?");
           Optional<ButtonType> option = alert.showAndWait();
           if(option.get()==ButtonType.OK){
               
           Stage primaryStage = new Stage(); 
        Parent roott = null;
        try {
            roott = FXMLLoader.load(getClass().getResource("/interfaces/recuperPassword.fxml"));
        } catch (Exception e) {
                        e.printStackTrace();

        }

        Scene scene = new Scene(roott);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("mot de passe oublier");
        primaryStage.show();
           }
           

    }
        
       
      
       
   
        
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
    
    
    
}
