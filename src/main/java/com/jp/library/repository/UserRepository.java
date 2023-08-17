package com.jp.library.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jp.library.entity.User;

@Mapper
public interface UserRepository {
	User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
