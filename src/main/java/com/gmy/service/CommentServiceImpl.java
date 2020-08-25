package com.gmy.service;

import com.gmy.dao.CommentRepository;
import com.gmy.pojo.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Kevin Guo
 * @package com.gmy.service
 * @date 2020/8/24 20:41
 * @description
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentIsNull(blogId, sort);
        return eachComment(comments);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1) {
            comment.setParentComment(commentRepository.getOne(parentCommentId));
        } else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    private List<Comment> eachComment(List<Comment> comments) {
        /**
         * @author: Kevin Guo
         * @date: 2020/8/25 14:33
         * @param: comments
         * @return: {@link List< Comment>}
         * @description: 循环每个顶级的留言结点
         */
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    private void combineChildren(List<Comment> comments) {
        /**
         * @author: Kevin Guo
         * @date: 2020/8/25 14:32
         * @param: comments root根节点，blog不为空的对象集合
         * @return: {@link }
         * @description:
         */
        for (Comment comment : comments) {
            List<Comment> replies = comment.getReplyComments();
            for (Comment reply : replies) {
                //循环迭代，找出子代，存放在 tempReplies 中
                recursively(reply);
            }
            //修改顶级结点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplies);
            //清除临时存放区
            tempReplies = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplies = new ArrayList<>();

    private void recursively(Comment comment) {
        /**
         * @author: Kevin Guo
         * @date: 2020/8/25 14:30
         * @param: comment 被迭代对象
         * @return: {@link }
         * @description: 递归迭代
         */
        tempReplies.add(comment);//顶结点添加到临时存放集合
        if (comment.getReplyComments().size() > 0) {
            List<Comment> replies = comment.getReplyComments();
            for (Comment reply : replies) {
                tempReplies.add(reply);
                if (reply.getReplyComments().size() > 0) {
                    recursively(reply);
                }
            }
        }
    }

}
