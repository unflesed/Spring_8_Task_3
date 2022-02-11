package com.itvdn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer age;
    private String name;
    private Integer id;

    public static String makeFriends(Student student1, Student student2) {
        return String.format("%s and %s are friends!", student1.getName(), student2.getName());
    }

    public static String getUserName() {
        throw new UnsupportedOperationException("No implementation!!!");
    }

    public static void waitInQueue() {
        try {
            Thread.sleep(30L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
