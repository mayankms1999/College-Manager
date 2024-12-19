package com.week3.mapping.Controller;

import com.week3.mapping.Entity.Student;
import com.week3.mapping.Service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/Students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public Optional<Student> getStudentDetail(@PathVariable long id){
        return studentService.getStudentDetail(id);
    }

    @PutMapping(path = "/{studentId}/professor/{professorId}")
    public Optional<Student> assignProfessorToStudent(@PathVariable Long studentId,
                                                      @PathVariable Long professorId) {
        return studentService.assignProfessorToStudent(studentId, professorId);
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student){
        return studentService.save(student);
    }

}
