package com.estsoft.springmybatis.repository;

import com.estsoft.springmybatis.model.Students;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    int countStudents();

    List<Students> selectStudents();

    void deleteStudent(int id);

    int updateStudent(Students students);
}
