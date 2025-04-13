package com.estsoft.demo.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Person {
    @Id
    private Long id;
    private String name;
    private int age;
    private List<String> hobbies;
}
