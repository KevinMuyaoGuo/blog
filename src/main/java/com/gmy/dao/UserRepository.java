package com.gmy.dao;

import com.gmy.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description
 * @Package com.gmy.dao
 * @Author Kevin Guo
 * @Date 2020/8/10
 */
public interface UserRepository extends JpaRepository<User,Long> { //User：dao操作对象；Long：主键类型

    User findByUsernameAndPassword(String username, String password);
}
