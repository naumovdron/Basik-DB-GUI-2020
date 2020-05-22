package result;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.*;
import java.util.Scanner;

import lab4.dbwork.DBConnection;
import lab4.parser.CommandParser;
import lab5.MainSceneController;

public class Main extends Application {

    static int N = 10;

    public static void main(String[] args) {
        try(Connection connection = DBConnection.getConnection(); Statement statement = connection.createStatement()) {
            System.out.println("CREATING TABLE...");
            statement.executeUpdate("CREATE TABLE products (id INTEGER PRIMARY KEY AUTO_INCREMENT, prodid INTEGER, title VARCHAR(255) UNIQUE , cost INTEGER)");
            System.out.println("SUCCESS");

            System.out.println("FILLING TABLE...");
            for (int i = 1; i <= N; i++) {
                statement.executeUpdate("INSERT INTO products(prodid, title, cost) VALUE (" + i + ", 'product" + i + "', " + i * 100 + ")");
            }
            System.out.println("SUCCESS");

            Application.launch();

            //try(Scanner in = new Scanner(System.in)) {
                //while (CommandParser.parse(in).execute()) {}
            //}

            System.out.println("REMOVING TABLE...");
            statement.executeUpdate("DROP TABLE products");
            System.out.println("SUCCESS");
        } catch (SQLException e) {
            System.out.println("SOMETHING WENT WRONG");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("../lab5/main.fxml"));

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("../lab5/main.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        //MainSceneController controller = loader.getController();
        //controller.actionChoiceBox = new ChoiceBox<String>(FXCollections.observableArrayList("Add", "Change Price", "Delete", "Price"));

        stage.setScene(new Scene(root));
        stage.setTitle("Products");
        stage.show();
    }
}
