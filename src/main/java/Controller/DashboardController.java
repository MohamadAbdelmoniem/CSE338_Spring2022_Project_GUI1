package Controller;

import com.example.cse338_spring2022_project.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Button BackButton;

    @FXML
    private Button PayBills;

    @FXML
    private Button ShowBalance;

    @FXML
    private Button TransactionsHistory;

    @FXML
    private Button TransferMoney;

    @FXML
    void BackButtonOnAction(ActionEvent event) {
        GoLogin();

    }

    @FXML
    void PayBillsOnAction(ActionEvent event) {
        GoPayBills();

    }

    @FXML
    void ShowBalanceOnAction(ActionEvent event) {
        GoShowBalance();
    }

    @FXML
    void TransactionsHistoryOnAction(ActionEvent event) {
        GoTransactionsHistory();
    }

    @FXML
    void TransferMoneyOnAction(ActionEvent event) {
        GoTransferMoney();
    }
    public void GoTransferMoney(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Transfer Money.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 517, 310);
            Stage stage = (Stage) TransferMoney.getScene().getWindow();
            //Stage stage=new Stage();
            stage.setTitle("TransferMoney");
            stage.setScene(scene);
            stage.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void GoTransactionsHistory() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Transaction History.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 719, 447);
            Stage stage = (Stage) TransactionsHistory.getScene().getWindow();
            //Stage stage=new Stage();
            stage.setTitle("Transaction History");
            stage.setScene(scene);
            stage.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void GoShowBalance(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ShowBalance.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 408, 168);
            Stage stage = (Stage) ShowBalance.getScene().getWindow();
            //Stage stage=new Stage();
            stage.setTitle("Transaction History");
            stage.setScene(scene);
            stage.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void GoPayBills() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PayBills.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 461, 400);
            Stage stage = (Stage) PayBills.getScene().getWindow();
            //Stage stage = new Stage();
            stage.setTitle("PayBills");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
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

}
