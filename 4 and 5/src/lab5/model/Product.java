package lab5.model;

public class Product {
    private Integer prodId;
    private String title;
    private Integer price;

    public Product(int prodId, String title, int price) {
        this.prodId = prodId;
        this.title = title;
        this.price = price;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prodId=" + prodId +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
