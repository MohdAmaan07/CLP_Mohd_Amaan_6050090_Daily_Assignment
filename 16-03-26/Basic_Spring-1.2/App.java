package com.demo.springcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.springcore.beans.Employee;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("springconf.xml");
        Employee emp = (Employee) context.getBean("emp");
        System.out.println(emp);
    }
}
