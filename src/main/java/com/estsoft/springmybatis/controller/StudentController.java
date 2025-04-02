package com.estsoft.springmybatis.controller;

import com.estsoft.springmybatis.model.Students;
import com.estsoft.springmybatis.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/students/count")
    public int StudentsC() {
        return studentService.countAll();
    }

    @GetMapping("/students")
    public List<Students> getStudentList() {
        return studentService.selectStudents();
    }

    @DeleteMapping("/deleteStudent")
    public void deleteStudent(@RequestParam int id) {
        studentService.deleteStudent(id);
    }

    @PutMapping("/updateStudent")
    public int updateStudent(@RequestBody Students students) {
        return studentService.updateStudent(students);
    }
}
