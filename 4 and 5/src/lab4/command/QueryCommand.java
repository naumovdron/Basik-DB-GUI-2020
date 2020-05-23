package lab4.command;

import lab4.dbwork.DBConnection;
import lab5.model.Product;
import strategy.OutStrategy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryCommand implements Command {
    private String query;
    private Connection connection;
    private OutStrategy outStrategy;

    public QueryCommand(String query, OutStrategy outStrategy) {
        this.query = query;
        this.outStrategy = outStrategy;
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            this.outStrategy.out("SOMETHING WENT WRONG: " + e.getMessage());
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
            outStrategy.out(products);
        } catch (SQLException e) {
            outStrategy.out("SOMETHING WENT WRONG: " + e.getMessage());
        }
        return true;
    }
}
