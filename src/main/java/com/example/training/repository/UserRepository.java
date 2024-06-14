package com.example.training.repository;

import com.example.training.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface UserRepository {
    @Select("select * from user")
    @ResultMap("UserResultMap")
    List<User> findAll();

    @Select("select username, password from user where username = #{username}")
    @ResultMap("UserResultMap")
    Optional findByUsername(@Param("username") String username);

    @Insert("INSERT INTO user (username,password, name, gender, dateOfBirth) VALUES (#{username},#{password}, #{name}, #{gender}, #{dob})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

}
