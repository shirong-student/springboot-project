package com.example.jpa;

import lombok.Data;

import javax.persistence.*;

@Data
// JPA ORM框架：对象（Java）和对象（数据库）的映射
@Entity // 指向数据库的表
@Table(name = "tb_user") // 指定表名
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 主键自增
    Integer id;
    @Column(length = 20)
    String userName;
    String code;
    String email;
    @Column(name = "phone")
    String mobile;
    Integer gender;
    Integer age;
    String password;
    Integer status;
    Integer departmentId;

    public User() {

    }

    public User(String code, String email, Integer gender) {
        this.code = code;
        this.email = email;
        this.gender = gender;
    }


}