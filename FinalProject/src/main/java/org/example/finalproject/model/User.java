package org.example.finalproject.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public abstract class User {
    protected int userId;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected String type;
    private static int counter = 0;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.type = type;
        userId = ++counter;
    }
    public User(int userId, String firstName, String lastName, String email, String password, String type) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.type = type;
    }
}
