package com.week3.mapping.Service;

import com.week3.mapping.Entity.Professor;
import com.week3.mapping.Entity.Student;

import com.week3.mapping.Repository.ProfessorRepository;
import com.week3.mapping.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;

    public StudentService(StudentRepository studentRepository, ProfessorRepository professorRepository) {
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
    }



    public Optional<Student> assignProfessorToStudent(Long studentId, Long professorId) {
        // Fetch student and professor entities from the database
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor not found with ID: " + professorId));

        // Add the professor to the student's list of professors
        student.getProfessors().add(professor);

        // Persist the updated student entity
        return Optional.of(studentRepository.save(student));
    }
    public Optional<Student> getStudentDetail(long id){
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }
}
