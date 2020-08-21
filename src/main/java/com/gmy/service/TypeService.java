package com.gmy.service;

import com.gmy.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Kevin Guo
 * @package com.gmy.service
 * @date 2020/8/20 14:52
 * @description
 */
public interface TypeService {

    //新增
    Type saveType(Type type);

    //通过id查询
    Type getTypeById(Long id);

    //通过名称查询
    Type getTypeByName(String name);

    //分页查询
    Page<Type> listType(Pageable pageable);

    //查询所有
    List<Type> listType();

    //修改
    Type updateType(Long id, Type type);

    //删除
    void deleteType(Long id);
}
