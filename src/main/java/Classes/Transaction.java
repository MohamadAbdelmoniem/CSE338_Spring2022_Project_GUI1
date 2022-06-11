package Classes;

import java.util.Date;
public class Transaction {
    String TransactionNumber;
    String UserAccount;
    String TransferNumberAccount;
    String date;
    String type;
    String amount;
    String description;

    public Transaction(String transactionNumber, String userAccount, String transferNumberAccount, String date, String type, String amount, String description) {
        TransactionNumber = transactionNumber;
        UserAccount = userAccount;
        TransferNumberAccount = transferNumberAccount;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public String getTransactionNumber() {
        return TransactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        TransactionNumber = transactionNumber;
    }

    public String getUserAccount() {
        return UserAccount;
    }

    public void setUserAccount(String userAccount) {
        UserAccount = userAccount;
    }

    public String getTransferNumberAccount() {
        return TransferNumberAccount;
    }

    public void setTransferNumberAccount(String transferNumberAccount) {
        TransferNumberAccount = transferNumberAccount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
