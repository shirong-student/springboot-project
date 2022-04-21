package com.example.junit;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/junit_computer")
public class Computer {

    @GetMapping("/addGet")
    public Integer addGet(Integer num1, Integer num2) {
        return num1 + num2;
    }

    @PostMapping("/mulPost")
    public Integer mulPost(Integer num1, Integer num2) {
        return num1 * num2;
    }

    @PostMapping("/JsonPost")
    public Integer JsonPost(@RequestBody AddParam addParam) {
        return addParam.getNum1() + addParam.getNum2();
    }
}
