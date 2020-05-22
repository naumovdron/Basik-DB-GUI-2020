package lab4.command;

import lab4.dbwork.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateCommand implements Command {
    public UpdateCommand(String update) {
        this.update = update;
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            System.out.println("SOMETHING WENT WRONG");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public boolean execute() {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(update);
        } catch (SQLException e) {
            System.out.println("SOMETHING WENT WRONG");
            System.err.println(e.getMessage());
        }
        return true;
    }

    private String update;
    private Connection connection;
}
