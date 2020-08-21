package com.gmy.service;

import com.gmy.pojo.Blog;
import com.gmy.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Kevin Guo
 * @package com.gmy.service
 * @date 2020/8/21 13:11
 * @description
 */
public interface BlogService {

    //新增
    Blog saveBlog(Blog blog);

    //通过id查询
    Blog getBlogById(Long id);

    //分页查询
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    //修改
    Blog updateBlog(Long id, Blog blog);

    //删除
    void deleteBlog(Long id);

}
