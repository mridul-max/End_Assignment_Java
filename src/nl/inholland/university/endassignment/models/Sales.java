package nl.inholland.university.endassignment.models;

import java.time.LocalDateTime;

public class Sales extends Person{

    public Sales(String firstName, String lastName, String userName, String password, LocalDateTime dateTime, Role role,int personId) {
        super(firstName, lastName, userName, password, dateTime, role,personId);
    }

}
