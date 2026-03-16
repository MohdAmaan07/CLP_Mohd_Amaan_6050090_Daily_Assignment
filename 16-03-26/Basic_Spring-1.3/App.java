package com.demo.springcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.springcore.beans.SBU;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("springconf.xml");
        SBU sbu = (SBU) context.getBean("sbu");
        System.out.println(sbu);
    }
}
