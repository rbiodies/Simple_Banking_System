package banking;

public class SecretMenu {

    static void secretMenu() {
        System.out.println("Welcome! You have unlocked the secret menu!\n");
        System.out.println("1. Accounts");
        System.out.println("0. Back");
        System.out.print(">");

        String action = Main.scanner.nextLine();

        if (action.equals("1")) {
            Main.state = BankingSystemStatus.ACCOUNTS;
        } else if (action.equals("0")) {
            Main.state = BankingSystemStatus.MENU;
            System.out.println("You have exited the secret menu!");
        } else {
            System.out.println("Wrong arguments!");
        }
    }

    static void selectAccounts() {
        Main.app.selectAccounts();
        Main.state = BankingSystemStatus.SECRETMENU;
    }
}
