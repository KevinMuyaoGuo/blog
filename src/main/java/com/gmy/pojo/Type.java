package com.gmy.pojo;

import lombok.AllArgsConstructor;
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
@Entity(name = "t_type")
@Table
@Data
@NoArgsConstructor
public class Type {

    @Id
    @GeneratedValue
    private Long id;//主键id

    private String name;//分类名

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();//该类型对应的所有文章


}
