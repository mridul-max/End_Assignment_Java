package nl.inholland.university.endassignment.data;

import nl.inholland.university.endassignment.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private List<Manager>managers = new ArrayList<>();
    private List<Sales> sales = new ArrayList<>();
    private List<Customer>customers = new ArrayList<>();
    public List<Manager> getManagers() { return managers; }
    public List<Sales> getSales() { return sales; }
    public List<Customer> getCustomers() { return customers; }
    public UserDao(){
        //adding manager in a list
        managers.add(new Manager("Mahedi","Mridul","mahedi123","mahedi123", LocalDateTime.now(), Role.MANAGER,101));
        managers.add(new Manager("Gara","umbrell","gara123","gara123",LocalDateTime.now(),Role.MANAGER,102));

        //adding salesman in a list
        sales.add(new Sales("Rick","Hein","rick123","rick123",LocalDateTime.now(),Role.SALES,201));
        sales.add(new Sales("Piet","Wim","piet123","piet123",LocalDateTime.now(),Role.SALES,202));

        //adding customer in a list
        customers.add(new Customer("Wim","Wiltenburg","wim","wim12",LocalDateTime.now(),Role.CUSTOMER,"Stentorstraat 90","Amsterdam","06-123456789","wim@email.com",1001));
        customers.add(new Customer("Jack","Traven","jack","jack12",LocalDateTime.now(),Role.CUSTOMER,"Dorpstraat 10","Arnhem","06-87654321","jack@email.com",1002));
        customers.add(new Customer("Jenny","Gump","jenny","jenny12",LocalDateTime.now(),Role.CUSTOMER,"Churchillallee 141","Den-Haag","06-14253648","jenny@email.com",1003));

    }

    public Person getByCredentials(String username, String password){

        Person p=null;

        for (Manager m:managers
             ) {
            if (m.getUserName().equals(username) && m.getPassword().equals(password)) {
                return m;
            }
        }
        for (Sales s:sales
        ) {
            if (s.getUserName().equals(username) && s.getPassword().equals(password)) {
                return s;
            }
        }
        return p;
    }
}
