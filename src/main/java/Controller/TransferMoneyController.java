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
import java.util.Random;

public class TransferMoneyController {

    @FXML
    private TextField AccountOneText;

    @FXML
    private Button BackButton;

    @FXML
    private TextField AccountTwoText;

    @FXML
    private TextField AmountText;

    @FXML
    private TextField DateText;

    @FXML
    private TextField DescriptionText;

    @FXML
    private Button TransferButton;


    @FXML
    private Label TransferMessage;


    public void TransferMessageOnAction(ActionEvent e) throws SQLException {
        String FirstAccount = AccountOneText.getText();
        String SecondAccount = AccountTwoText.getText();
        String Amount = AmountText.getText();
        String Date = DateText.getText();
        String Description = DescriptionText.getText();
        if (FirstAccount.isBlank() == false || SecondAccount.isBlank() == false ||
                Amount.isBlank() == false || Date.isBlank() == false) {
            ValidateTransfer();
        } else {
            TransferMessage.setText("Enter all data fields");
        }
    }
    @FXML
    void TransferButtonOnAction(ActionEvent event) {

    }
    public void ValidateTransfer()throws SQLException {
        String FirstAccount = AccountOneText.getText();
        String SecondAccount = AccountTwoText.getText();
        int Amount = Integer.parseInt(AmountText.getText());
        String Date = DateText.getText();
        String Description = DescriptionText.getText();
        if (FirstAccount.equals(LoginController.Flag)) {
            String GetBalance1="Select Balance FROM useraccount where idUserAccount='"+FirstAccount+"'";
            Random rand = new Random();
            int maxNumber = 10000000;
            int randomNumber = rand.nextInt(maxNumber) + 1;
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            try {
                String VertifyTransfer = "INSERT INTO world.transactions (`TransactionNumber`, `AccountNumber`, `FromToAccount`, `Amount`, `DebitCredit`, `Date`, `Description`) " + "VALUES ('" + randomNumber + "', '" + FirstAccount + "','" + SecondAccount + "','" + Amount + "','" + "D" + "','" + Date + "','" + Description + "')";
                Statement statement = connectDB.createStatement();
                ResultSet queryResult = statement.executeQuery(GetBalance1);
                while (queryResult.next()) {
                    if (queryResult.getInt(1) > Amount) {
                        Statement ss = connectDB.createStatement();
                        ss.executeUpdate(VertifyTransfer);
                        TransferMessage.setText("Validate");
                        int Balance = queryResult.getInt(1);
                        Balance -= Amount;
                        String UpBalance = "UPDATE useraccount SET Balance='" + Balance + "'where idUserAccount='" + FirstAccount + "'";
                        Statement s2 = connectDB.createStatement();
                        s2.executeUpdate(UpBalance);
                        queryResult.next();
                    }
                    else{
                        TransferMessage.setText("No Balance");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                TransferMessage.setText("Wrong ID");
            }
            catch (Exception e) {
                e.printStackTrace();
            }

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
