package com.gmy.service;

import com.gmy.dao.UserRepository;
import com.gmy.pojo.User;
import com.gmy.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Package com.gmy.service
 * @Author Kevin Guo
 * @Date 2020/8/10
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
