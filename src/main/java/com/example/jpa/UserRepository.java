package com.example.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("from User where userName = ?1")
    List<User> getByName(String userName);

    // 根据用户名和密码查询用户（用户名不允许重复）：@Query
    @Query("from User where userName = ?1 and password = ?2")
    List<User> getNamePassword(String userName, String password);

    // 根据年龄段和性别查询用户
    @Query("from User where age between ?1 and ?2 and gender = ?3")
    List<User> getAgeSex(Integer ageMin, Integer ageMax, Integer gender);

    // 根据年龄段和性别查询用户@Query：使用参数别名
    @Query("from User where age between :min and :max and gender=:sex")
    List<User> getAgeSex1(@Param("min") Integer ageMin, @Param("max") Integer ageMax, @Param("sex") Integer gender);

    // 如何查询部分字段
    // 根据用户名查询用户编号，假设用户名不重复
    @Query("select code from User where userName=:name")
    String getCode(@Param("name") String userName);

    // 根据用户名查询用户编号，邮箱，性别，假设用户名不重复
    @Query("select new com.example.jpa.UserVo(code,email,gender) from User where userName=:name")
    UserVo getCodeEmailGender(@Param("name") String userName);


    // 根据用户名查询用户编号，邮箱，性别，假设用户名不重复
    @Query("select new com.example.jpa.User(code,email,gender) from User where userName=:name")
    User getCodeEmailGender2(@Param("name") String userName);

    // 根据性别查询用户编号，邮箱，性别
    @Query("select new com.example.jpa.User(code,email,gender) from User where gender=:gender")
    List<User> getCodeEmailGender3(@Param("gender") Integer gender);

    // 用户名为条件更新性别的接口
    @Transactional // 事务：原子性，发生异常时可以回滚
    @Modifying
    @Query("update User set gender=:sex where userName=:name")
    int updateGender(@Param("name") String userName, @Param("sex") Integer gender);

    // 用户名为条件删除性别的接口
    @Transactional // 事务：原子性，发生异常时可以回滚
    @Modifying
    @Query("delete from User where userName=:name")
    int deleteGender(@Param("name") String userName);

    // 多表联查
    // 查询所有用户的编号、姓名、邮箱、电话、及其所在部门名称。
    // select u.code,u.user_name,u.gender,u.email,u.phone,d.name from tb_user u,tb_department d where u.department_id=d.id
    @Query("select new com.example.jpa.UserDepartVo(u.userName,u.code,u.email,u.gender,u.mobile,d.name) " +
            "from  User  u,Department d where u.departmentId=d.id")
    List<UserDepartVo> selectUserDepart();

    // 查询所有男用户的编号、姓名、邮箱、电话、及其所在部门名称。
    // select u.code,u.user_name,u.gender,u.email,u.phone,d.name from tb_user u,tb_department d where u.department_id=d.id and u.gender=1
    @Query("select new com.example.jpa.UserDepartVo(u.userName,u.code,u.email,u.gender,u.mobile,d.name) " +
            "from  User  u,Department d where u.departmentId=d.id and u.gender=111")
    List<UserDepartVo> selectUserDepart2();

    // 查询人工智能学院所有男用户的编号、姓名、邮箱、电话、及其所在部门名称。
    // select u.code,u.user_name,u.gender,u.email,u.phone,d.name from tb_user u,tb_department d where u.department_id=d.id and gender=1 and d.name='人工智能学院'
    @Query("select new com.example.jpa.UserDepartVo(u.userName,u.code,u.email,u.gender,u.mobile,d.name) " +
            "from  User  u,Department d where u.departmentId=d.id and u.gender=111 and d.name='人工智能学院'")
    List<UserDepartVo> selectUserDepart3();
}
