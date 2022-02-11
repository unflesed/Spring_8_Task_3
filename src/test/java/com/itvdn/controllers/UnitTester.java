package com.itvdn.controllers;

import com.itvdn.model.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/app-simple-ctx.xml"})
public class UnitTester {
    @Autowired
    List<Student> studentList;
    @Autowired
    @Qualifier(value = "friend1")
    Student student;

    @Test
    public void testUser() {
        Assert.assertEquals("Oleg", student.getName());
    }

    @Test
    public void testFriendship() {
        System.out.println("******************");
        System.out.println("info about friends");
        Iterator<Student> it = studentList.iterator();
        while (it.hasNext()) {
            Student tempUser = it.next();
            if (it.hasNext()) {
                System.out.println(Student.makeFriends(tempUser, it.next()));
            }
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUser2() {
        Student.getUserName();
    }

    @Test(timeout = 15L)
    public void testUser3() {
        Student.waitInQueue();
    }

    @Test(timeout = 60L)
    public void testUser4() {
        Student.waitInQueue();
    }
}
