package Controller;

import com.example.cse338_spring2022_project.DatabaseConnection;
import com.example.cse338_spring2022_project.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class SignUpController {
    boolean found = false;

    @FXML
    private TextField CPass;

    @FXML
    private Button BackButton;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField ID;

    @FXML
    private Label SignUpMesaage;

    @FXML
    private TextField LastName;

    @FXML
    private TextField Pass;

    @FXML
    private TextField UserName;

    @FXML
    void BackButtonOnAction(ActionEvent event) {
        GoLogin();

    }
    public void GoLogin(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 520, 400);
            Stage stage = (Stage) BackButton.getScene().getWindow();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void SignmeUP(ActionEvent event) throws SQLException {
        ValidateSignUp();
    }

    void ValidateSignUp() throws SQLException {
        boolean found1 = false;
        if (!FirstName.getText().isEmpty() && !Pass.getText().isEmpty() && !CPass.getText().isEmpty() && !ID.getText().isEmpty() && !LastName.getText().isEmpty() && !UserName.getText().isEmpty()) {
            if (CPass.getText().equals(Pass.getText())) {
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectDB = connectNow.getConnection();
                try {
                    String sql = "SELECT count(1) FROM world.useraccount WHERE idUserAccount = '" + ID.getText() + "'";
                    Statement ss = connectDB.createStatement();
                    ResultSet queryResult= ss.executeQuery(sql);
                    while (queryResult.next()) {
                        if (queryResult.getInt(1) == 1) {
                            found=true;
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if (!found) {
                    String AddUser = "INSERT INTO `world`.`useraccount` (`idUserAccount`, `Username`, `Password`, `FirstName`, `LastName`, `Balance`) " +
                            "VALUES ('" + ID.getText() + "', '" + UserName.getText() + "', '" + Pass.getText() + "', '" + FirstName.getText() + "', '" + LastName.getText() + "', '" + 1000 + "')";
                    try {
                        Statement ss = connectDB.createStatement();
                        ss.executeUpdate(AddUser);
                        SignUpMesaage.setText("User have successfully sign up!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    SignUpMesaage.setText("This ID is already used");
                    SignUpMesaage.setText("This ID is already used");
                }
            } else {
                SignUpMesaage.setText("Password doesn't match");
            }
        } else {
            SignUpMesaage.setText("Please provide all info");
        }
    }
}
