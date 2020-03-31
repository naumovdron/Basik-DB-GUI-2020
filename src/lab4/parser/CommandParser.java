package lab4.parser;

import lab4.commands.*;
import java.util.Scanner;

public class CommandParser {
    public static Command parse(Scanner in) {
        String[] strings = in.nextLine().split(" ");
        if (strings[0].equals("/exit") && strings.length == 1) {
            return new ExitCommand();
        } else if (strings[0].equals("/help") && strings.length == 1) {
            return new ReportCommand("/exit\n/help\n/add\n/delete\n/show_all\n/price\n/change_price\n/filter_by_price");
        } else if (strings[0].equals("/add") && strings.length == 3) {

        } else if (strings[0].equals("/delete") && strings.length == 2) {

        } else if (strings[0].equals("/show_all") && strings.length == 1) {

        } else if (strings[0].equals("/price") && strings.length == 2) {

        } else if (strings[0].equals("/change_price") && strings.length == 3) {

        } else if (strings[0].equals("/filter_by_price") && strings.length == 3) {

        }
        return new ReportCommand("WRONG COMMAND");
    }
}
