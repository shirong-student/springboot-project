package com.example.GlobalExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一校验异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolationException(HttpServletResponse response,
                                                   ConstraintViolationException exception) throws IOException {
        String[] messages = exception.getMessage().split(",");
        response.setContentType("text/html; charset=UTF-8");
        for (String message : messages) {
            response.getWriter().println(message);
        }
    }

//     Json格式返回 https://www.cnblogs.com/xl4ng/p/15380148.html
    @ExceptionHandler(BindException.class)
    public void handleBindException(HttpServletResponse response, BindException exception) throws IOException {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        ArrayList<Object> list = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            Map<String, String> map = new HashMap<>();
            map.put("field", fieldError.getField());
            map.put("message", fieldError.getDefaultMessage());
            map.put("code", fieldError.getCode());
            list.add(map);
        }
        response.setContentType("application/json; charset=UTF-8");
        // Spring Boot的默认json转换工具：jackson
        // 可用于将任何 Java 值序列化为字符串的方法
        // 功能上等同于使用StringWriter调用writeValue(Writer, Object)并构造 String，但效率更高
        ObjectMapper mapper = new ObjectMapper();
        String jacksonValue = mapper.writeValueAsString(list);
        response.getWriter().println(jacksonValue);
    }
}