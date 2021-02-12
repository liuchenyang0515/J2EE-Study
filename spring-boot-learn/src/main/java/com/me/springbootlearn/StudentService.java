package com.me.springbootlearn;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描述：学生Service
 */
@Service
public class StudentService {
    @Resource
    private StudentMapper studentMapper;

    public Student findStudent(Integer id) {
        return studentMapper.findById(id);
    }
}
