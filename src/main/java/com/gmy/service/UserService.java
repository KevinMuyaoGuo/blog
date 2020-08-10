package com.gmy.service;

import com.gmy.pojo.User;

/**
 * @Description
 * @Package com.gmy.service
 * @Author Kevin Guo
 * @Date 2020/8/10
 */
public interface UserService {

    User checkUser(String username, String password);
}
