package com.example.query.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String major;
}
