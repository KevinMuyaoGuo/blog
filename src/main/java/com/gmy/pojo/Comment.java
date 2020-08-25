package com.gmy.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Package com.gmy.pojo
 * @Author Kevin Guo
 * @Date 2020/8/7
 */
@Entity(name = "t_comment")
@Table
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Long id;//主键id

    private String nickname;//用户昵称

    private String email;//用户邮箱

    private String content;//留言内容

    private String avatar;//头像地址

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;//评论创建时间

    @ManyToOne
    private Blog blog;//该评论对应的文章

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();

    @ManyToOne
    private Comment parentComment;

    private boolean adminComment;
}
