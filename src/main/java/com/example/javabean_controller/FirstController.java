package com.example.javabean_controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * ***************************** *
 * 热部署快捷键: Ctrl + Shift + F9
 * ***************************** *
 */

// @RestController注解，实现@Controller和@ResponseBody两个注解的组合作用，标志该类为接口类文件
// @RestController实现将当前类作为控制类添加到Spring容器中，并将返回结果直接写入 HTTP response body 中，返回给请求端。

@RestController
public class FirstController {

    // @GetMapping注解的主要作用是设置方法的访问路径并限定其访问方式为Get，
    // hello方法的请求路径为“/hello”，方法的返回值为"Hello World"的字符串对象。
    @GetMapping("/hello")
    public String hello() {
        return "Hello World" + 1 + 5;
    }

    @GetMapping("/add1")
    public String add1(String num1, String num2) {
        return num1 + num2;
    }

    @GetMapping("/add2")
    public Integer add2(@RequestParam(value = "a", required = false, defaultValue = "0") Integer num1,
                        @RequestParam(value = "b", required = false, defaultValue = "0") Integer num2) {
        return num1 + num2;
    }

    @PostMapping("/add3")
    public Integer add3(@RequestParam(value = "a", required = false, defaultValue = "0") Integer num1,
                        @RequestParam(value = "b", required = false, defaultValue = "0") Integer num2) {
        System.out.println(num1);
        System.out.println(num2);
        return num1 + num2;
    }

    @GetMapping("/add4/{a}/{b}")
    public Integer add4(@PathVariable(value = "a") Integer num1,
                        @PathVariable(value = "b") Integer num2) {
        return num1 + num2;
    }

    @GetMapping("/add5/{num1}/{num2}")
    public Integer add5(@PathVariable Integer num1, @PathVariable Integer num2) {
        return num1 + num2;
    }

    @PostMapping("/add6/{num1}/{num2}")
    public Integer add6(@PathVariable Integer num1, @PathVariable Integer num2) {
        return num1 + num2;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        return "ID:" + id + " delete success";
    }

    @CrossOrigin(maxAge = 3600)
    @RequestMapping(value = "/saveQuestions")
    public Object saveTemplateData(@RequestParam int id, @RequestBody List<Object> questions) {
        System.out.println(id);
        System.out.println(questions);
        return questions.get(0);
    }

    @GetMapping("/testJavaBean")
    public TestJavaBeanParam testJavaBean(TestJavaBeanParam testJavaBeanParam) {
        testJavaBeanParam.setStrTemp("你好，对象已经接收，这是临时填充的数据~~");
        return testJavaBeanParam;
    }

    @PostMapping("/testJson")
    public TestJavaBeanParam testJson(@RequestBody TestJavaBeanParam testJavaBeanParam) {
        testJavaBeanParam.setStrTemp("你好，Json数据已经接收，这是临时填充的数据~~");
        return testJavaBeanParam;
    }

    @GetMapping("/addArr")
    public String[] addArr(String[] arr) {
        return arr;
    }

    @PostMapping("/addArray")
    public TestJavaBeanParam addArray(@RequestBody TestJavaBeanParam arr) {
        return arr;
    }

    @GetMapping("/sendCode/{telephone}")
    public String addArr(@PathVariable String telephone) {
        if (!isMobile(telephone)) {
            return "验证码发送失败 非法电话号码";
        }
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            val.append(random.nextInt(10));
        }
        return "验证码：" + val;
    }

    public static boolean isMobile(String str) {
        //制定验证条件
        String regex2 = "^((13[0-9])|(14[579])|(15([0-3,5-9]))|(16[6])|(17[0135678])|(18[0-9]|19[89]))\\d{8}$";
        Pattern pattern = Pattern.compile(regex2);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}

