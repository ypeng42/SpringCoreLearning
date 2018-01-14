package com.experiment;

import com.learn.HelloWorld;

public class MainApp {
    public static void main(String[] args) {
        MockApplicationContext context = new MockApplicationContext("SpringSetting.xml");

        HelloWorld obj = (HelloWorld) context.getBean("helloBean");
        obj.printHello();
    }
}
