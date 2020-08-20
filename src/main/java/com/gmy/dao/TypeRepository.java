package com.gmy.dao;

import com.gmy.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kevin Guo
 * @package com.gmy.dao
 * @date 2020/8/20 14:58
 * @description
 */
public interface TypeRepository extends JpaRepository<Type, Long> {

    Type findByName(String name);
}
