package org.example.finalproject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
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
        students = new ArrayList<>();
    }

    public Group(Topic topic) {
        groupId = ++counter;
        students = new ArrayList<>();
        this.topic = topic;
    }

    public Group(int groupId, List<Student> students, Topic topic) {
        this.groupId = groupId;
    }
}
