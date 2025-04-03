//package com.estsoft.springmybatis.controller;
//
//import com.estsoft.springmybatis.model.Students;
//import com.estsoft.springmybatis.service.StudentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class StudentController {
//    private final StudentService studentService;
//
//    @GetMapping("/students/count")
//    public int StudentsC() {
//        return studentService.countAll();
//    }
//
//    @GetMapping("/students")
//    public List<Students> getStudentList() {
//        return studentService.selectStudents();
//    }
//
//    @PostMapping("/insertStudent")
//    public int insertStudent(@RequestBody Students student) {
//        return studentService.insertStudent(student);
//    }
//
//    @DeleteMapping("/deleteStudent/{id}")
//    public void deleteStudent(@PathVariable int id) {
//        studentService.deleteStudent(id);
//    }
//
//    @PutMapping("/updateStudent/{id}")
//    public int updateStudent(@PathVariable int id, @RequestBody Students students) {
//        students.setId(id);
//        return studentService.updateStudent(students);
//    }
//}
