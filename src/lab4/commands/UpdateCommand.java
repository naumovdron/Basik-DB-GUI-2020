package lab4.commands;

import lab4.dbwork.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateCommand implements Command {
    public UpdateCommand(String update) {
        this.update = update;
    }

    @Override
    public boolean execute() {
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(update);
        } catch (SQLException e) {
            System.out.println("SOMETHING WENT WRONG");
            System.err.println(e.getMessage());
        }
        return true;
    }

    private String update;
}
