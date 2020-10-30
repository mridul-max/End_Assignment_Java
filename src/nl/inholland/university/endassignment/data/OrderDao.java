package nl.inholland.university.endassignment.data;

import nl.inholland.university.endassignment.models.Article;
import nl.inholland.university.endassignment.models.Order;
import nl.inholland.university.endassignment.models.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private DataSeed dB;

    public List<Order> getOrders() {
        return dB.orders;
    }

    public OrderDao(DataSeed dataSeed) {

        this.dB = dataSeed;
    }
    public void insert(Order order){
        this.dB.orders.add(order);
        this.dB.orderItems.addAll(order.getOrderItems());
        for (OrderItem m:order.getOrderItems()
             ) {
            int amount = m.getArticle().getQuantity() - m.getArticleAmount();
            m.getArticle().setQuantity(amount);
        }
    }
}
