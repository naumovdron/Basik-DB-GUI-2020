package strategy;

import lab5.model.Product;

import java.util.List;

public class ConsoleStrategy implements OutStrategy {
    @Override
    public void out(String message) {
        System.out.println(message);
    }

    @Override
    public void out(List<Product> products) {
        for (Product i : products) {
            System.out.println(i.toString());
        }
    }
}
