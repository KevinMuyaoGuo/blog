package com.gmy.dao;

import com.gmy.pojo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kevin Guo
 * @package com.gmy.dao
 * @date 2020/8/21 10:27
 * @description
 */
public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByName(String name);
}
