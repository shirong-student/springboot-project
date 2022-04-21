package com.example.javabean_controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * java.util.Date类型的日期，使用 2020/01/01 发送
 * java.sql.Date使用 2020-01-01 格式发送。
 */
@Data
public class TestJavaBeanParam {
    Integer id;
    String name;
    String sex;
    Integer age;
    String address;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    Date birthday;
    String strTemp;
    TestJavaBeanParamTwo testJavaBeanParamTwo;
    String[] arr;
}
