package com.example.javabean_controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;
@Data
public class TestJavaBeanParamTwo {
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    Date updateTime;
}
