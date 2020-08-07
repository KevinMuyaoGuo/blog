package com.gmy.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Package com.gmy.pojo
 * @Author Kevin Guo
 * @Date 2020/8/7
 */
@Entity
@Table(name = "t_tag")
@Data
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue
    private Long id;//主键id

    private String name;//标签名

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();//该标签对应的所有文章


}
