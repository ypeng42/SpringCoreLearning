package com.learn;

import java.util.Arrays;

public class StreamLearn {
    public static void main(String[] args) {
        Arrays.asList("12,20,100,101", "200,27,3,4,5")
                .stream()
                .map(str -> str.split(","))
                .flatMap(l -> Arrays.stream(l))
                .map(Integer::parseInt)
                .filter(i -> i > 10)
                .forEach(System.out::println);
    }
}
