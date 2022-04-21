package com.example.jpa;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String code;
    @Column(length = 20)
    String name;
    String location;
}
