package banking;

import java.util.Scanner;

public class LogMenu {
    final static Scanner scanner = new Scanner(System.in);

    static void logMenu() {
        System.out.println("1. Balance");
        System.out.println("2. Add income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");
        System.out.print(">");

        chooseActionLogMenu(Main.scanner.nextLine());
    }

    static void chooseActionLogMenu(String action) {
        switch (action) {
            case "1":
                Main.state = BankingSystemStatus.BALANCE;
                break;
            case "2":
                Main.state = BankingSystemStatus.INCOME;
                break;
            case "3":
                Main.state = BankingSystemStatus.TRANSFER;
                break;
            case "4":
                Main.state = BankingSystemStatus.CLOSE;
                break;
            case "5":
                Main.state = BankingSystemStatus.MENU;
                System.out.println("\nYou have successfully logged out!");
                break;
            case "0":
                Main.state = BankingSystemStatus.EXIT;
                break;
            default:
                System.out.println("\nWrong action!");
                break;
        }
    }

    static void selectBalance() {
        int balance = Main.app.getBalance(Main.id);

        System.out.printf("Balance: %d\n", balance);

        Main.state = BankingSystemStatus.LOGMENU;
    }

    static void addIncome() {
        System.out.println("Enter income:");
        System.out.print(">");

        Main.app.addIncome(Main.id, scanner.nextInt());
        Main.state = BankingSystemStatus.LOGMENU;
    }

    static void doTransfer() {
        System.out.println("Transfer");
        System.out.println("Enter card number:");
        System.out.print(">");

        String number = Main.scanner.nextLine();

        if (number.equals(Main.number)) {
            System.out.println("You can't transfer money to the same account!");
        } else if (number.length() != 16 || !number.matches("\\d++")
                || Integer.parseInt(number.substring(number.length() - 1)) != Main.findChecksum(number.substring(0, number.length() - 1))) {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
        } else if (!Main.app.isExistingNumber(number)) {
            System.out.println("Such a card does not exist.");
        } else {
            System.out.println("Enter how much money you want to transfer:");
            System.out.print(">");

            int money = scanner.nextInt();

            if (Main.app.getBalance(Main.id) < money) {
                System.out.println("Not enough money!");
            } else {
                Main.app.doTransfer(Main.number, money, number);
            }
        }
        Main.state = BankingSystemStatus.LOGMENU;
    }

    static void closeAccount() {
        Main.app.closeAccount(Main.id);
        Main.state = BankingSystemStatus.MENU;
    }
}
