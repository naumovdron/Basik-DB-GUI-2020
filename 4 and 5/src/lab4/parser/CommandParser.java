package lab4.parser;

import lab4.command.*;
import java.util.Scanner;

public class CommandParser {
    public static Command parse(Scanner in) {
        String[] strings = in.nextLine().split(" ");
        if (strings[0].equals("/exit") && strings.length == 1) {
            return new ExitCommand();
        } else if (strings[0].equals("/help") && strings.length == 1) {
            return new ReportCommand("/exit\n/help\n/add\n/delete\n/show_all\n/price\n/change_price\n/filter_by_price");
        } else if (strings[0].equals("/add") && strings.length == 3) {
            return new UpdateCommand("INSERT INTO products(title, cost) VALUE ('" + strings[1] + "', " + strings[2] + ")");
        } else if (strings[0].equals("/delete") && strings.length == 2) {
            return new UpdateCommand("DELETE FROM products WHERE title = '" + strings[1] + "'");
        } else if (strings[0].equals("/show_all") && strings.length == 1) {
            return new QueryCommand("SELECT * FROM products");
        } else if (strings[0].equals("/price") && strings.length == 2) {
            return new QueryCommand("SELECT cost FROM products WHERE title = '" + strings[1] + "'");
        } else if (strings[0].equals("/change_price") && strings.length == 3) {
            return new UpdateCommand("UPDATE products SET cost = " + strings[2] + " WHERE title = '" + strings[1] + "'");
        } else if (strings[0].equals("/filter_by_price") && strings.length == 3) {
            return new QueryCommand("SELECT * FROM products WHERE cost BETWEEN " + strings[1] + " AND " + strings[2]);
        }
        return new ReportCommand("WRONG COMMAND");
    }
}
