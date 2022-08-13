package banking;

import java.sql.*;

/**
 *
 * @author sqlitetutorial.net
 */
public class InsertApp {
    private final Connection conn;

    public InsertApp(Connection conn) {
        this.conn = conn;
    }

    public void createTable() {
        // Statement предназначен для выполнения простых SQL-запросов без параметров
        // Больше подходит использование Statement для запросов DDL (CREATE TABLE, DROP TABLE)
        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS card " +
                    "(id INTEGER PRIMARY KEY, " +
                    " number TEXT, " +
                    "pin TEXT, " +
                    " balance INTEGER DEFAULT 0)";

            // выполняет такие SQL-команды, как INSERT, UPDATE, DELETE, CREATE и
            // возвращает количество измененных строк
            stmt.executeUpdate(sql);
//            System.out.println("Created table in given database...");
        } catch (Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    /**
     * Insert a new row into the card table
     *
     * @param number
     * @param pin
     */
    public void insertAccount(String number, String pin) {
        String sql = "INSERT INTO card(number,pin) VALUES(?,?)";

        // PreparedStatement используется для выполнения SQL-запросов с или без входных параметров
        // Больше подходит использование PreparedStatement для запросов DML (INSERT, UPDATE или DELETE)
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, number);
            pstmt.setString(2, pin);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isExistingNumber(String number) {
        String sql = "SELECT * from card WHERE number LIKE ?; ";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, number);
            // выполняет команду SELECT, которая возвращает данные в виде ResultSet
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public int isExistingAccount(String number, String pin) {
        String sql = "SELECT * from card WHERE number LIKE ? AND pin LIKE ?; ";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, number);
            pstmt.setString(2, pin);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
//                JOptionPane.showMessageDialog(null, "You have successfully logged in!");
                return rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public void selectAccounts() {
        String sql = "SELECT * from card; ";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            System.out.printf("%-10s%-20s%-10s%-10s%n", "id", "number", "pin", "balance");
            while (rs.next()) {
                System.out.printf("%-10d", rs.getInt("id"));
                System.out.printf("%-20s", rs.getString("number"));
                System.out.printf("%-10s", rs.getString("pin"));
                System.out.printf("%-10d\n", rs.getInt("balance"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getBalance(int id) {
        String sql = "SELECT * from card WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return (rs.getInt("balance"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void addIncome(int id, int income) {
        String sql = "UPDATE card SET balance = balance + ? WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, income);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

            System.out.println("Income was added!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void doTransfer(String numberSender, int income, String numberReceiver) {
        String insertSenderSQL = "UPDATE card " +
                "SET balance = balance - ? WHERE number = ?";

        String insertReceiverSQL = "UPDATE card " +
                "SET balance = balance + ? WHERE number = ?";

        try {

            // Disable auto-commit mode
            conn.setAutoCommit(false);

            try (PreparedStatement insertSender = conn.prepareStatement(insertSenderSQL);
                 PreparedStatement insertReceiver = conn.prepareStatement(insertReceiverSQL)) {

                // Insert an sender
                insertSender.setInt(1, income);
                insertSender.setString(2, numberSender);
                insertSender.executeUpdate();

                // Insert an receiver
                insertReceiver.setInt(1, income);
                insertReceiver.setString(2, numberReceiver);
                insertReceiver.executeUpdate();

                conn.commit();

                System.out.println("Success!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeAccount(int id) {
        String sql = "DELETE FROM card WHERE id=?;";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("The account has been closed!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
