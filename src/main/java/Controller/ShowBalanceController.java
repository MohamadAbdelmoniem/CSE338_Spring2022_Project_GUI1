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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShowBalanceController {

    @FXML
    private Button BackButton;

    @FXML
    private TextField AccountNumberText;

    @FXML
    private Label BalanceMessage;

    @FXML
    private Button ShowBalanceButton;

    public void BalanceMessageOnAction(ActionEvent e){
        if (AccountNumberText.getText().isBlank()==false) {
            ShowBalance();
        } else {
            BalanceMessage.setText("enter user id");
        }
    }
    public void ShowBalance(){
        DatabaseConnection connectNow= new DatabaseConnection();
        Connection connectDB=connectNow.getConnection();
        try{
            if(AccountNumberText.getText().equals(LoginController.Flag)) {
                        String Balance = "SELECT Balance FROM world.useraccount WHERE idUserAccount='" + AccountNumberText.getText() + "'";
                        Statement ss = connectDB.createStatement();
                        ResultSet r = ss.executeQuery(Balance);
                        r.next();
                        BalanceMessage.setText(r.getString(1));
                    }
            else{
                BalanceMessage.setText("Please enter correct ID");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    void BackButtonOnAction(ActionEvent event) {
        GODashboard();
    }
    public void GODashboard(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Dashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 697, 447);
            Stage stage = (Stage) BackButton.getScene().getWindow();
            //Stage stage=new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            stage.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
