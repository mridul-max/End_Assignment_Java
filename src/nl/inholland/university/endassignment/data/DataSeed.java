package nl.inholland.university.endassignment.data;

import nl.inholland.university.endassignment.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataSeed {
    public static DataSeed instance;
    public List<Manager> managers = new ArrayList<>();
    public List<Sales> sales = new ArrayList<>();
    public List<Customer>customers = new ArrayList<>();
    public List<Article> articles= new ArrayList<>();
    public List<Order> orders = new ArrayList<>();
    public List<OrderItem>orderItems = new ArrayList<>();

    private DataSeed(){
        //adding manager in a list
        managers.add(new Manager("Mahedi","Mridul","mahedi123","mahedi123", LocalDateTime.now(), Role.MANAGER,101));
        managers.add(new Manager("Gara","umbrell","gara123","gara123",LocalDateTime.now(),Role.MANAGER,102));

        //adding salesman in a list
        sales.add(new Sales("Rick","Hein","rick123","rick123",LocalDateTime.now(),Role.SALES,201));
        sales.add(new Sales("Piet","Wim","piet123","piet123",LocalDateTime.now(),Role.SALES,202));

        //adding customer in a list

        Customer c1 = new Customer("Wim","Wiltenburg","wim","wim12",LocalDateTime.now(),Role.CUSTOMER,"Stentorstraat 90","Amsterdam","06-123456789","wim@email.com",1001);
        Customer c2 = new Customer("Jack","Traven","jack","jack12",LocalDateTime.now(),Role.CUSTOMER,"Dorpstraat 10","Arnhem","06-87654321","jack@email.com",1002);
        Customer c3 = new Customer("Jenny","Gump","jenny","jenny12",LocalDateTime.now(),Role.CUSTOMER,"Churchillallee 141","Den-Haag","06-14253648","jenny@email.com",1003);

        customers.add(c1);
        customers.add(c2);
        customers.add(c3);

        //adding articles to a list
        Article a1 = new Article("Fender","Telecaster",false, Type.REGULAR,1079.79,100);
        Article a2 =new Article("Fender","Precision",false, Type.BASS,1300.49,21);
        Article a3 = new Article("Simon Patrick","Pro Flame Maple",true,Type.REGULAR,1290.7,1);

        articles.add(a1);
        articles.add(a2);
        articles.add(a3);

        //adding orderItem to the List

        //adding orders to a list
        Order order = new Order();
        OrderItem firstOrderItem= new OrderItem(order,a1,4);
        OrderItem secondOrderItem= new OrderItem(order,a2,5);
        OrderItem thirdOrderItem= new OrderItem(order,a3,4);
        orderItems.add(firstOrderItem);
        orderItems.add(secondOrderItem);
        orderItems.add(thirdOrderItem);

        order.addOrderItem(firstOrderItem);
        order.addOrderItem(secondOrderItem);
        order.addOrderItem(thirdOrderItem);
        Date date = new Date();
        order.setDate(date.toString());
        order.addCustomer(c1);
        order.addCustomer(c2);
        order.addCustomer(c3);
        orders.add(order);
    }
    public static DataSeed getDbInstance(){
        if(instance==null){
            instance= new DataSeed();
        }
        return instance;

    }
}
