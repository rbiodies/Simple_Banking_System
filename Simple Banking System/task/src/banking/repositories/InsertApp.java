package banking.repositories;

public interface InsertApp {
    void createTable();
    void insertAccount(String number, String pin);
    boolean isExistingNumber(String number);
    int isExistingAccount(String number, String pin);
    void selectAccounts();
    int getBalance(int id);
    void addIncome(int id, int income);
    void doTransfer(String numberSender, int income, String numberReceiver);
    void closeAccount(int id);
}
