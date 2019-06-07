package com.zay.contacts.test;

import javax.validation.constraints.Email;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args){
        List<String> firstNames = Arrays.asList("Zied","Khadija","Mohamed","Ali","Mounira","Mounira");

        List<String> coll1 =  firstNames.stream().filter(s -> s.contains("Z")).collect(Collectors.toList());

        List<String> coll2 = firstNames.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());

        List<String> coll3 = firstNames.stream().distinct().collect(Collectors.toList());
        List<String> coll4 = firstNames.stream().limit(2).collect(Collectors.toList());
        List<String> coll5 = firstNames.stream().peek(s -> s.toUpperCase()).collect(Collectors.toList());



        coll1.forEach(System.out::println);
        System.out.println("**** MAP ****");
        coll2.forEach(System.out::println);
        System.out.println("**** DISTINCT ****");
        coll3.forEach(System.out::println);
        System.out.println("**** LIMIT ****");
        coll4.forEach(System.out::println);
        System.out.println("**** PEEK ****");
        coll5.forEach(System.out::println);
        System.out.println("**** LAZY ****");
        firstNames.stream().filter(s-> {
            System.out.println("############");
            return true ;
        }).collect(Collectors.toList());





    }
}
