package nl.inholland.university.endassignment.models;

import java.time.LocalDateTime;


public class Customer extends Person{

    private String streetAddress;
    private String city;
    private String phoneNumber;
    private String email;

    public Customer(String firstName, String lastName, String userName, String password, LocalDateTime dateTime,Role role,String streetAddress,String city,String phoneNumber,String email,int personId) {
        super(firstName, lastName, userName, password, dateTime,role,personId);
        this.streetAddress = streetAddress;
        this.city =city;
        this.phoneNumber=phoneNumber;
        this.email = email;
    }

    public String getStreetAddress() {

        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {

        this.streetAddress = streetAddress;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

}
