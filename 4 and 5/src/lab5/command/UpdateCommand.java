package lab5.command;

import javafx.scene.control.Label;
import lab4.command.Command;
import lab4.dbwork.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateCommand implements Command {
    private String update;
    private Connection connection;
    private Label errors;

    public UpdateCommand(String update, Label errors) {
        this.update = update;
        this.errors = errors;
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
            errors.setText(e.getMessage());
        }
        return true;
    }
}
