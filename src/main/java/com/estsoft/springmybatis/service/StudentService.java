//package com.estsoft.springmybatis.service;
//
//import com.estsoft.springmybatis.model.Students;
//import com.estsoft.springmybatis.repository.StudentMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class StudentService {
//    private final StudentMapper studentMapper;
//
//    public int countAll() {
//        return studentMapper.countStudents();
//    }
//
//    public List<Students> selectStudents() {
//        return studentMapper.selectStudents();
//    }
//
//    public int insertStudent(Students student) {
//        return studentMapper.insertStudent(student);
//    }
//
//    public void deleteStudent(int id) {
//        studentMapper.deleteStudent(id);
//    }
//
//    public int updateStudent(Students students) {
//        return studentMapper.updateStudent(students);
//    }
//}
