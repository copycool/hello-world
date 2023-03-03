package com.hngc.mapper;

import com.hngc.entry.User;

import java.util.List;

public interface UserMapper {
    int save(User user);

    List<User> select();

    int update(User user);

    int deleteById(int id);

}
