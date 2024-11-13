package org.example.finalproject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@EqualsAndHashCode@ToString
public class Group {
    private int groupId;
    private static int counter = 0;
    @Setter
    private List<Student> students;
    @Setter
    private Topic topic;

    public Group() {
        groupId = ++counter;
    }

    public Group(List<Student> students, Topic topic) {
        groupId = ++counter;
        this.students = students;
        this.topic = topic;
    }
}
