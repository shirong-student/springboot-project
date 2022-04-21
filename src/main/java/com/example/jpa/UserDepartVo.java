package com.example.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 无参构造
@AllArgsConstructor // 全参构造
public class UserDepartVo {
    String userName;
    String code;
    String email;
    Integer gender;
    String mobile;

    String name;
}
