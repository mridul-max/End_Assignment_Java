package nl.inholland.university.endassignment.data;

import nl.inholland.university.endassignment.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private DataSeed dB;
    private List<Manager>managers = new ArrayList<>();
    private List<Sales> sales = new ArrayList<>();
    private List<Customer>customers = new ArrayList<>();
    public List<Manager> getManagers() {
        return dB.managers;
    }
    public List<Sales> getSales() {
        return dB.sales;
    }
    public List<Customer> getCustomers() {
        return dB.customers;
    }

    public UserDao(DataSeed dataSeed){
        this.dB =dataSeed;
    }

    public Person getByCredentials(String username, String password){

        Person p=null;

        for (Manager m:getManagers()
             ) {
            if (m.getUserName().equals(username) && m.getPassword().equals(password)) {
                return m;
            }
        }
        for (Sales s:getSales()
        ) {
            if (s.getUserName().equals(username) && s.getPassword().equals(password)) {
                return s;
            }
        }
        return p;
    }
}
