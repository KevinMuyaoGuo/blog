package com.gmy.service;

import com.gmy.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Kevin Guo
 * @package com.gmy.service
 * @date 2020/8/21 10:28
 * @description
 */
public interface TagService {

    //新增
    Tag saveTag(Tag tag);

    //通过id查询
    Tag getTagById(Long id);

    //通过名称查询
    Tag getTagByName(String name);

    //分页查询
    Page<Tag> listTag(Pageable pageable);

    //查询所有
    List<Tag> listTag();

    List<Tag> listTag(String ids);

    //修改
    Tag updateTag(Long id, Tag tag);

    //删除
    void deleteTag(Long id);
}
