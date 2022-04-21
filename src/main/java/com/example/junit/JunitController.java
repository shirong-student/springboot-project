package com.example.junit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("junit_test")
public class JunitController {

    @GetMapping("/test_string")
    public String test_string() {
        return "hello world";
    }

    @GetMapping("/test_ArrayList")
    public ArrayList<Integer> test_ArrayList(ArrayList<Integer> list) {
        if (list.size() == 0) return null;
        return list;
    }

    @GetMapping("/test_JavaBean")
    public User test_JavaBean(User user) {
        return user;
    }

    @GetMapping("/add")
    public Integer add(Integer num1, Integer num2) {
        return num1 + num2;
    }
}
