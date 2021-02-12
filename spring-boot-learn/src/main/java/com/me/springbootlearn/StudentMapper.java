package com.me.springbootlearn;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 描述：学生Mapper
 */
@Mapper
@Repository
public interface StudentMapper {
    @Select("select * from students where id = #{id}")
    Student findById(Integer id);
}
