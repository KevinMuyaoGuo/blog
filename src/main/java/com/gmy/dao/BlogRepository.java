package com.gmy.dao;

import com.gmy.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Kevin Guo
 * @package com.gmy.dao
 * @date 2020/8/21 13:15
 * @description
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from t_blog b where b.recommend = true and b.published = true")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from t_blog b where b.published = true")
    Page<Blog> findAll(Pageable pageable);

    @Query("select b from t_blog b where (lower(b.title) like ?1 or lower(b.content) like ?1 or lower(b.description) like ?1) and (b.published = true)")
    Page<Blog> findByQuery(String query, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update t_blog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);

    @Query("select function('date_format', b.updateTime, '%Y') as year from t_blog b group by year order by function('date_format', b.updateTime, '%Y') desc")
    List<String> findGroupYear();

    @Query("select b from t_blog b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);
}
