package org.example.finalproject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Teacher extends User {
    protected List<Group> groups;
    protected List<Topic> topics;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String email, String password, String type, List<Group> groups, List<Topic> topics) {
        super(firstName, lastName, email, password, type);
        this.groups = groups;
        this.topics = topics;
    }
}
