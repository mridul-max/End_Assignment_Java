package nl.inholland.university.endassignment.models;

import nl.inholland.university.endassignment.data.DataSeed;
import nl.inholland.university.endassignment.data.OrderDao;

import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {


    private Customer customer;
    private int id;
    private static int autoId =0;

    private String customerName = "";


    private String city = "";

    public String getPhone() {
        return phone;
    }

    private String phone ="";

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    private String email = "";
    private String date ="";

    public int getItemCount() {
        return itemCount;
    }

    private int itemCount = 0;
    private double total = 0;
    public Order(){
    this.id =++autoId;
    }
    public void addCustomer(Customer customer){
        this.customer = customer;
        this.customerName = customer.getFirstName();
        this.city = customer.getCity();
        this.phone =customer.getPhoneNumber();
        this.email =customer.getEmail();

    }

    public String getCity() {
        return city;
    }
    public String getCustomerName() {
        return customerName;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }
    public List<OrderItem> orderItems = new ArrayList<>();

    public List<OrderItem> getOrderItems() {

        return orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.itemCount++;
        this.total = orderItem.getPrice()* orderItem.getArticleAmount();
        this.orderItems.add(orderItem);
    }
    public void saveOrder(){
        Date date = new Date();
        this.setDate(date.toString());
        OrderDao orderDao = new OrderDao(DataSeed.getDbInstance());
        orderDao.insert(this);
    }

}
