package nl.inholland.university.endassignment.models;

import java.time.LocalDateTime;

public abstract class Person {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Role role;
    private int personId;
    private LocalDateTime dateTime;
    public Person(String firstName, String lastName, String userName, String password,LocalDateTime dateTime,Role role,int personId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.dateTime = dateTime;
        this.role = role;
        this.personId =personId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

}
