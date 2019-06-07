package com.zay.contacts.test.flatmap;

import java.util.Arrays;
import java.util.List;

public class TestFlatMap1 {

    public static void main(String[] args){
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        List<String[]> list  = Arrays.asList(data);

        list.stream().filter(s -> s[0].contains("a")).forEach(e->{
            System.out.println("("+e[0]+","+e[1]+")");
        });


        list.stream().flatMap(e -> Arrays.stream(e) ).map(String::toUpperCase).forEach(System.out::println);


    }
}
