package com.example.validator;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class User {
    @Null(groups = {Add.class}, message = "新增时,id必须为空")
    @NotNull(groups = {Update.class}, message = "更新时id不能为空")
    Integer id;
    @NotBlank(message = "用户名不能为空")
    String name;
    String sex;
    @NotNull
    @Min(value = 0, message = "年龄不能小于0岁")
    @Max(value = 120, message = "不能大于120岁")
    Integer age;
    @NotNull
    String address;
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    Date birthday;
    @Email
    String email;
    @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "手机号格式错误")
    String phone;
}