package com.reflectionLearn;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        for (Constructor<?> constructor : TestBean.class.getDeclaredConstructors()) {
            System.out.println(constructor.getName());
        }

        TestBean bean1 = new TestBean();
        try {
            /*
             * getMethod return public methods
             * getDeclaredMethod return all methods
             */
            Method method = TestBean.class.getDeclaredMethod("setJob", String.class);
            method.setAccessible(true);
            method.invoke(bean1, "invoked from method");

            System.out.println(bean1.getJob());
        } catch (Exception e) {

        }

    }
}
