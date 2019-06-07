package com.zay.contacts.test.flatmap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestExample2 {
    public static void main(String[] args) {
        Student obj1 = new Student();
        obj1.setName("zied");
        obj1.addBook("Java 8 in Action");
        obj1.addBook("Spring Boot in Action");
        obj1.addBook("Effective Java (2nd Edition)");

        Student obj2 = new Student();
        obj2.setName("khadija");
        obj2.addBook("Learning Python, 5th Edition");
        obj2.addBook("Effective Java (2nd Edition)");

        List<Student> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);


        list.stream().map(e -> e.getBook())
                .flatMap(s -> s.stream().map(b -> b.toUpperCase()))
                .distinct()
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);


    }

}
