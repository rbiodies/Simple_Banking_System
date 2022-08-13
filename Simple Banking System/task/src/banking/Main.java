package banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

enum BankingSystemStatus {
    MENU, CREATE, LOG, SECRETMENU, ACCOUNTS, LOGMENU, BALANCE, INCOME, TRANSFER, CLOSE, EXIT
}

class BankingSystem {

    static void bankingSystem() {
        switch (Main.state) {
            case MENU:
                Main.mainMenu();
                break;
            case CREATE:
                Main.createAccount();
                break;
            case LOG:
                Main.logAccount();
                break;
            case SECRETMENU:
                SecretMenu.secretMenu();
                break;
            case ACCOUNTS:
                SecretMenu.selectAccounts();
                break;
            case LOGMENU:
                LogMenu.logMenu();
                break;
            case BALANCE:
                LogMenu.selectBalance();
                break;
            case INCOME:
                LogMenu.addIncome();
                break;
            case TRANSFER:
                LogMenu.doTransfer();
                break;
            case CLOSE:
                LogMenu.closeAccount();
                break;
        }
    }
}

public class Main {
    static BankingSystemStatus state = BankingSystemStatus.MENU;
    final static Scanner scanner = new Scanner(System.in);
    static InsertApp app;
    static String number;
    static int id;

    /**
     *
     * Connect to the db.s3db database
     *
     * @return the Connection object
     */
    private static Connection connect(String fileName) {
        // SQLite connection string
        String url = "jdbc:sqlite:" + fileName;
        Connection conn = null;

        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
//        System.out.println("Connection to SQLite has been established.");
        return conn;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 2 || !args[0].equals("-fileName")) {
            System.err.println("Usage: -fileName <file_name>");
        } else {
            try (Connection connection = connect(args[1])) {
                app = new InsertApp(connection);
                app.createTable();
                do {
                    BankingSystem.bankingSystem();
                    System.out.print("\n");
                } while (state != BankingSystemStatus.EXIT);
                scanner.close();

                System.out.println("Bye!");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static void mainMenu() {
        System.out.println("1. Create account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
        System.out.print(">");

        chooseActionMainMenu(scanner.nextLine());
    }

//    static String  inputNumeric() {
//        while (true) {
//            System.out.print(">");
//            String  str = scanner.nextLine();
//            if (str.matches("\\d++")) {
//                return str;
//            }
//            System.out.println("\nInput String has not integer type!\n");
//        }
//    }

    static void chooseActionMainMenu(String action) {
        switch (action) {
            case "1":
                state = BankingSystemStatus.CREATE;
                break;
            case "2":
                state = BankingSystemStatus.LOG;
                break;
            case "0":
                state = BankingSystemStatus.EXIT;
                break;
            case "admin":
                state = BankingSystemStatus.SECRETMENU;
                break;
            default:
                System.out.println("\nWrong action!");
                break;
        }
    }

    static void createAccount() {
        Random  random = new Random();
        String number;

        do {
            // 4000 00
            String generateNumber = "400000" + String.format("%09d", (long) (Math.random() * 1000000000L));
            // determine the last digit of the bank card number,
            // so that the sum of 16 digits of the bank card is divisible by 10 without a remainder
            number = generateNumber + findChecksum(generateNumber);
        } while (app.isExistingNumber(number));

        String pin = String.format("%04d", random.nextInt(10000));

        // insert three new rows
        app.insertAccount(number, pin);

        System.out.println("Your card has been created");
        System.out.println("Your card number:");
        System.out.println(number);
        System.out.println("Your card PIN:");
        System.out.println(pin);

        state = BankingSystemStatus.MENU;
    }

    static int findChecksum(String generateNumber) {
        String[] str = generateNumber.split("");
        int[] array = new int[str.length];

        for (int i = 0; i < str.length; i++) {
            array[i] = Integer.parseInt(str[i]);
        }

        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 0) {
                array[i] *= 2;
                if (array[i] > 9) {
                    array[i] -= 9;
                }
            }
            sum += array[i];
        }

        int checksum = 0;

        if (sum % 10 != 0) {
            checksum = 10 - sum % 10;
        }
        return checksum;
    }

    static void logAccount() {
        System.out.println("Enter your card number:");
        System.out.print(">");
        number = scanner.nextLine();
        System.out.println("Enter your PIN:");
        System.out.print(">");
        String pin = scanner.nextLine();
        System.out.print("\n");

        id = app.isExistingAccount(number, pin);

        if (id != -1) {
            System.out.println("You have successfully logged in!");
            state = BankingSystemStatus.LOGMENU;
        } else {
            System.out.println("Wrong card number or PIN!");
            state = BankingSystemStatus.MENU;
        }
    }
}