package nl.inholland.university.endassignment.models;

public class OrderItem {
    private Order order;
    private Article article;
    private  int articleAmount;
    private String brand;
    private String model;
    private boolean acoustic;
    private Type type;
    private double price;

    private static int autoId =0;

    public int getOrderId() {
        return orderId;
    }

    private int orderId;
    public OrderItem(Order order,Article article, int articleAmount) {
        this.order = order;
        this.orderId =++autoId;
        this.article = article;
        this.brand =article.getBrand();
        this.articleAmount = articleAmount;
        this.model = article.getModel();
        this.acoustic=article.isAcoustic();
        this.type =article.getType();
        this.price=article.getPrice();
    }
    public Article getArticle() {
        return article;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public boolean isAcoustic() {
        return acoustic;
    }

    public Type getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public Order getOrder() {

        return order;
    }


    public int getArticleAmount() {

        return articleAmount;
    }


}
