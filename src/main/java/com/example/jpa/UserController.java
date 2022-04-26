package com.example.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.PageRanges;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    final
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    public String add(User user) {
        userRepository.save(user);
        return "add success";
    }

    @PostMapping("/delete")
    public String delete(Integer id) {
        userRepository.deleteById(id);
        return "delete success";
    }

    @PostMapping("/update")
    public String update(User user) {
        userRepository.save(user);
        return "update success";
    }

    // 根据id查询
    @GetMapping("/list")
    public String list(Integer id) {
        userRepository.findById(id);
        return "add success";
    }

    // 查询所有
    @GetMapping("/listAll")
    public String listAll() {
        userRepository.findAll();
        return "add success";
    }

    @GetMapping("/byName")
    public List<User> byName(String userName) {
        return userRepository.getByName(userName);
    }

    @GetMapping("/getNamePassword")
    public List<User> getNamePassword(String userName, String password) {
        return userRepository.getNamePassword(userName, password);
    }

    @GetMapping("/getAgeSex")
    public List<User> getAgeSex(Integer ageMin, Integer ageMax, Integer gender) {
        return userRepository.getAgeSex(ageMin, ageMax, gender);
    }

    @PostMapping("/getCode")
    public String getCode(String name) {
        return userRepository.getCode(name);
    }

    @PostMapping("/getCodeEmailGender")
    public UserVo getCodeEmailGender(String name) {
        return userRepository.getCodeEmailGender(name);
    }

    @PostMapping("/getCodeEmailGender2")
    public User getCodeEmailGender2(String name) {
        return userRepository.getCodeEmailGender2(name);
    }

    @PostMapping("/getCodeEmailGender3")
    public List<User> getCodeEmailGender3(Integer gender) {
        return userRepository.getCodeEmailGender3(gender);
    }

    @PostMapping("/updateGender")
    public int updateGender(String userName, Integer gender) {
        return userRepository.updateGender(userName, gender);
    }

    @PostMapping("/deleteGender")
    public int deleteGender(String userName) {
        return userRepository.deleteGender(userName);
    }

    @PostMapping("/selectUserDepart")
    public List<UserDepartVo> selectUserDepart() {
        return userRepository.selectUserDepart();
    }

    @PostMapping("/selectUserDepart2")
    public List<UserDepartVo> selectUserDepart2() {
        return userRepository.selectUserDepart2();
    }

    @PostMapping("/selectUserDepart3")
    public List<UserDepartVo> selectUserDepart3() {
        return userRepository.selectUserDepart3();
    }

    @PostMapping("/getNamePasswordSql")
    public List<User> getNamePasswordSql(String userName, String password) {
        return userRepository.getNamePasswordSql(userName, password);
    }

    @PostMapping("/selectUserDepartSql")
    public List<UserDepartInterVo> selectUserDepartSql() {
        return userRepository.selectUserDepartSql();
    }

//    @PostMapping("/selectUserDepartSql")
//    public List<UserDepartInterVo> selectUserDepart3Sql() {
//        return userRepository.selectUserDepart3Sql();
//    }

    @PostMapping("/selectUserGenderSql")
    public Page<User> selectUserGenderSql(Integer gender, Integer currentPage, Integer sizePage) {
        Pageable pageable = PageRequest.of(currentPage, sizePage);
        return userRepository.selectUserGenderSql(gender, pageable);
    }

    @PostMapping("/selectSortUserGenderSql")
    public List<User> selectSortUserGenderSql(Integer gender) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return userRepository.selectSortUserGenderSql(gender, sort);
    }
}


