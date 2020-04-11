package lab4.commands;

import lab4.dbwork.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryCommand implements Command {
    public QueryCommand(String query) {
        this.query = query;
    }

    @Override
    public boolean execute() {
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                System.out.println(getStringResult(result));
            }
        } catch (SQLException e) {
            System.out.println("SOMETHING WENT WRONG");
            System.err.println(e.getMessage());
        }
        return true;
    }

    private String getStringResult(ResultSet result) {
        String string = "";
        try {
            int id = result.getInt("id");
            string += ("id: " + id + ' ');
        } catch (SQLException e) {}
        try {
            int prodid = result.getInt("prodid");
            string += ("prodid: " + prodid + ' ');
        } catch (SQLException e) {}
        try {
            String title = result.getString("title");
            string += ("title: '" + title + "' ");
        } catch (SQLException e) {}
        try {
            int cost = result.getInt("cost");
            string += ("cost: " + cost + ' ');
        } catch (SQLException e) {}
        return string;
    }

    private String query;
}
