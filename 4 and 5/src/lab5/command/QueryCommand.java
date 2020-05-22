package lab5.command;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import lab4.command.Command;
import lab4.dbwork.DBConnection;
import lab5.model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryCommand implements Command {
    private String query;
    private Connection connection;
    private TableView<Product> tableView;
    private Label errors;

    public QueryCommand(String query, TableView<Product> tableView, Label errors) {
        this.query = query;
        this.tableView = tableView;
        this.errors = errors;
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            this.errors.setText(e.getMessage());
            System.out.println("SOMETHING WENT WRONG");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public boolean execute() {
        try(Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query)) {
            List<Product> products = new ArrayList<>();
            while (result.next()) {
                products.add(new Product(
                                result.getInt("prodid"),
                                result.getString("title"),
                                result.getInt("cost"))
                );
            }
            tableView.getItems().addAll(products);
        } catch (SQLException e) {
            errors.setText(e.getMessage());
            System.out.println("SOMETHING WENT WRONG");
            System.err.println(e.getMessage());
        }
        return true;
    }
}
