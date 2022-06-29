package com.kata.crud.entity;

import lombok.Data;

@Data
public class Person {
    private Long id;
    private String name;
    private String lastName;
    private int age;
}
