package com.beanUtilLearn;

import com.learn.HelloWorld;
import org.apache.commons.beanutils.BeanUtils;

public class BeanUtilMain {
    public static void main(String[] args) {
        HelloWorld obj = new HelloWorld();
        try {
            /*
             * 1. figure out in general how set property works, work out the details later
             * 2. learn why it is coded this way
             */
            BeanUtils.setProperty(obj, "name", "bean util test");
            obj.printHello();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
