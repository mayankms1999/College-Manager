package com.week3.mapping.Service;

import com.week3.mapping.Entity.Professor;
import com.week3.mapping.Entity.Student;
import com.week3.mapping.Entity.Subject;
import com.week3.mapping.Repository.StudentRepository;
import com.week3.mapping.Repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    public SubjectService(SubjectRepository subjectRepository, StudentRepository studentRepository){
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }
    public Optional<Subject> getSubjectdetail(long id) {
        return subjectRepository.findById(id);
    }

    public Optional<Subject> save(Subject subject) {
        return Optional.of(subjectRepository.save(subject));
    }

    public Subject assignStudentToSubject(Long subjectId, List<Long> studentIds) {
        // Fetch the Subject entity
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with ID: " + subjectId));

        // Fetch the list of Students by their IDs
        List<Student> students = studentRepository.findAllById(studentIds);

        // Update the bidirectional relationship
        for (Student student : students) {
            if (!subject.getStudents().contains(student)) {
                subject.getStudents().add(student);
            }
            if (!student.getSubjects().contains(subject)) {
                student.getSubjects().add(subject);
            }
        }

        // Persist the changes
        subjectRepository.save(subject); // Save the subject with updated students
        studentRepository.saveAll(students); // Save all students with the updated subject relationship

        return subject; // Return the updated Subject
    }
}
