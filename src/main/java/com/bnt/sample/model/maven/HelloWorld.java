package com.bnt.sample.model.maven;

/**
 * TODO
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/8/26 下午4:44 bnt
 * @history
 */
public class HelloWorld {

    public String sayHello() {
        return "Hello Maven";
    }

    public static void main(String[] args) {
        System.out.println(new HelloWorld().sayHello());
    }
}
