package com.gmy.service;

import com.gmy.pojo.Comment;

import java.util.List;

/**
 * @author Kevin Guo
 * @package com.gmy.service
 * @date 2020/8/24 20:39
 * @description
 */
public interface CommentService {

    //查询
    List<Comment> listCommentByBlogId(Long blogId);

    //保存
    Comment saveComment(Comment comment);

    //删除

}
