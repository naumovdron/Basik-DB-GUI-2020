package strategy;

import lab5.model.Product;

import java.util.List;

public interface OutStrategy {
    void out(String message);
    void out(List<Product> products);
}
