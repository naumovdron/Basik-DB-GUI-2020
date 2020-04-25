package result;

import lab4.dbwork.DBConnection;
import lab4.parser.CommandParser;

import java.sql.*;
import java.util.Scanner;

/*Работа с БД.
        Сформировать таблицу товаров (id, prodid, title, cost) запросом из Java-приложения.
        При запуске приложения очищать таблицу и заполнять N товаров вида:
        id_товара 1 товар1 10
        Написать консольное приложение, которое позволяет:

        1. Добавить товар в таблицу, в табице не может быть 2 товара с одинаковым именем
        Пример:
        /add товар666 1050

        2. Удалить товар из таблицы	по имени.
        Пример:
        /delete товар1984

        3. Вывести все товары в консоль.
        Пример:
        /show_аll

        4. Узнать цену товара по его имени, либо если такого товара нет, то должно быть выведено сообщение "Такого товара нет".
        Пример:
        /price товар777

        5. Изменить цену товара.
        Пример:
        /change_price товар10 10000

        6. Вывести товары в заданном ценовом диапазоне цен.
        Пример:
        /filter_by_price 1000 10000

        Реализовать данное приложения пользуясь только средствами JDBC.
*/

public class Main {

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

            try(Scanner in = new Scanner(System.in)) {
                while (CommandParser.parse(in).execute()) {}
            }

            System.out.println("REMOVING TABLE...");
            statement.executeUpdate("DROP TABLE products");
            System.out.println("SUCCESS");
        } catch (SQLException e) {
            System.out.println("SOMETHING WENT WRONG");
            System.err.println(e.getMessage());
        }
    }
}
