package lab4.command;

import lab4.dbwork.DBConnection;
import strategy.OutStrategy;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateCommand implements Command {
    private String update;
    private Connection connection;
    private OutStrategy outStrategy;

    public UpdateCommand(String update, OutStrategy outStrategy) {
        this.update = update;
        this.outStrategy = outStrategy;
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            this.outStrategy.out("SOMETHING WENT WRONG: " + e.getMessage());
        }
    }

    @Override
    public boolean execute() {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(update);
        } catch (SQLException e) {
            outStrategy.out("SOMETHING WENT WRONG: " + e.getMessage());
        }
        return true;
    }
}
