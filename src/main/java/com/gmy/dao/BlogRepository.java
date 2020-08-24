package com.gmy.dao;

import com.gmy.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Kevin Guo
 * @package com.gmy.dao
 * @date 2020/8/21 13:15
 * @description
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from t_blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from t_blog b where lower(b.title) like ?1 or lower(b.content) like ?1 or lower(b.description) like ?1")
    Page<Blog> findByQuery(String Query, Pageable pageable);
}
