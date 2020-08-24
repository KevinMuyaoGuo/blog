package com.gmy.dao;

import com.gmy.pojo.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Kevin Guo
 * @package com.gmy.dao
 * @date 2020/8/24 22:05
 * @description
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBlogId(Long blogId, Sort sort);
}
