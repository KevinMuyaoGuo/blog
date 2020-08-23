package com.gmy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Package com.gmy.po
 * @Author Kevin Guo
 * @Date 2020/8/7
 */
@Entity(name = "t_blog")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id
    @GeneratedValue
    private Long id;//主键id

    private String title;//文章标题

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;//文章内容

    private String firstPicture;//文章首图

    private String flag;//原创、转载标签

    private Integer views;//浏览数

    private boolean appreciation;//文章是否开启赞赏

    private boolean shareStatement;//转载声明

    private boolean commentable;//文章是否开启评论

    private boolean published;//文章是否发布

    private boolean recommend;//文章是否推荐

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;//文章创建时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;//文章更新时间

    @ManyToOne
    private Type type;//文章类型

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();//文章所有标签

    @ManyToOne
    private User user;//文章发布者

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();//该文章对应的所有留言

    @Transient
    private String tagIds;
}
