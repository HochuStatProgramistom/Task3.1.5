package com.kata.crud;

import com.kata.crud.config.MyConfig;
import com.kata.crud.entity.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);

        List<Person> allPersons = communication.getAllPersons();
        System.out.println(allPersons);

    }
}
