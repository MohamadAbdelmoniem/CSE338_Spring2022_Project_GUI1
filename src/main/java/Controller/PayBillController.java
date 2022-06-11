package Controller;

import com.example.cse338_spring2022_project.DatabaseConnection;
import com.example.cse338_spring2022_project.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PayBillController {

    @FXML
    private TextField AccountNumber;

    @FXML
    private Button BackButton;

    @FXML
    private Label BalanceMessage;
    @FXML
    private ChoiceBox<String> ChooseBox;

    @FXML
    private TextField BillNumber;

    @FXML
    private Button PayBill;

    @FXML
    private Label Status;

    @FXML
    void StatusMassageOnAction(ActionEvent event) throws SQLException {
        PayBill();
    }

    private void PayBill() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        int TransactionAmount = 100;
        Random rand = new Random();
        int maxNumber = 10000000;
        int randomNumber = rand.nextInt(maxNumber) + 1;
        String PayBill = "SELECT Balance FROM `world`.`useraccount` where idUserAccount='"+AccountNumber.getText()+"'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(PayBill);
            int TransferAccountNumber;
            if(AccountNumber.getText().equals(LoginController.Flag)) {
                if (ChooseBox.getValue() == "WE") {
                    TransferAccountNumber = 32421;
                }
                while (queryResult.next()) {
                    if (queryResult.getInt(1) > TransactionAmount) {
                        String InsertTransaction = "INSERT INTO `world`.`transactions` (`TransactionNumber`, `AccountNumber`, `FromToAccount`, `Amount`, `DebitCredit`, `Date`, `Description`, `BillNumber`) " +
                                "VALUES ('" + randomNumber + "','" + AccountNumber.getText() + "', '" + 333 + "', '" + TransactionAmount + "', '" + "D" + "', '" + java.time.LocalDate.now() + "', 'Paying Bill', '" + BillNumber.getText() + "')";
                        Statement ss = connectDB.createStatement();
                        ss.executeUpdate(InsertTransaction);
                        Status.setText("Bill Paid");
                        String GetBalance="Select Balance FROM useraccount where idUserAccount='"+AccountNumber.getText()+"'";
                        Statement s= connectDB.createStatement();
                        ResultSet r= s.executeQuery(GetBalance);
                        r.next();
                         int Balance=r.getInt(1);
                         Balance=Balance-TransactionAmount;
                        String UpBalance="UPDATE useraccount SET Balance='"+Balance+"'where idUserAccount='"+AccountNumber.getText()+"'";
                        Statement s2 = connectDB.createStatement();
                        s2.executeUpdate(UpBalance);
                    } else {
                        Status.setText("No Balance ");
                    }
                }
            }
            else{
                Status.setText("Please enter correct ID");
            }
        } catch (Exception e) {
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
    @FXML
    void initialize() {
        ChooseBox.getItems().addAll("WE","Vodafone","Amazon","Orange");
    }
}
