package com.gmy.dao;

import com.gmy.pojo.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Kevin Guo
 * @package com.gmy.dao
 * @date 2020/8/21 13:15
 * @description
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {


}
