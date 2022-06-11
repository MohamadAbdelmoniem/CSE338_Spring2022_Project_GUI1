package Controller;
import com.example.cse338_spring2022_project.DatabaseConnection;
import com.example.cse338_spring2022_project.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class LoginController {
    static String Flag;
    @FXML
    private Button BackButton;

    @FXML
    private Button CancelButton;
    public void CancelButtonOnAction(ActionEvent e){
        Stage stage =(Stage) CancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Label LoginMessage;
    public void LoginMessageOnAction(ActionEvent e){
        if (UsernameTextField.getText().isBlank()==false && PasswordTextField.getText().isBlank()==false){
            //LoginMessage.setText("please enter username and password");
            ValidateLogin();

        }
        else{
            LoginMessage.setText("You cant login using blank data");
        }
    }
    public void ValidateLogin(){
        DatabaseConnection connectNow= new DatabaseConnection();
        Connection connectDB=connectNow.getConnection();
        String VertifyLogin="SELECT count(1) FROM world.useraccount WHERE Username='"+UsernameTextField.getText()+
                "' AND Password='"+PasswordTextField.getText()+"'";
        try{
            Statement statement= connectDB.createStatement();
            ResultSet queryResult= statement.executeQuery(VertifyLogin);
            while (queryResult.next()){
                if (queryResult.getInt(1)==1){
                    String ID="SELECT idUserAccount FROM world.useraccount WHERE Username='"+UsernameTextField.getText()+"'";
                    Statement ss= connectDB.createStatement();
                    ResultSet r= ss.executeQuery(ID);
                    r.next();
                    Flag=r.getString(1);
                    System.out.println(Flag);
                    GoDashbaord();
                    //try to male the account number valid acroos the procees
                    //LoginMessage.setText("Welcome!");
                    //dashbaordlogin();
                }
                else{
                    LoginMessage.setText("Wrong Username Or password");

                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void GoRegisterOnAction(ActionEvent event) {
        GoRegister();
    }
    @FXML
    private Button LoginButton;

    @FXML
    private PasswordField PasswordTextField;

    @FXML
    private TextField UsernameTextField;

    public void GoDashbaord(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Dashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 697, 447);
            Stage stage = (Stage) LoginButton.getScene().getWindow();
            //Stage stage=new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            stage.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    @FXML
    private Button Register;
    public void GoRegister(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 520, 383);
            Stage stage = (Stage) Register.getScene().getWindow();
            //Stage stage=new Stage();
            stage.setTitle("Sign Up");
            stage.setScene(scene);
            stage.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
