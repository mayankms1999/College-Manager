package com.week3.mapping.Service;

import com.week3.mapping.Entity.AdmissionRecord;
import com.week3.mapping.Entity.Professor;
import com.week3.mapping.Entity.Student;
import com.week3.mapping.Entity.Subject;
import com.week3.mapping.Repository.ProfessorRepository;
import com.week3.mapping.Repository.StudentRepository;
import com.week3.mapping.Repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;
    public ProfessorService(ProfessorRepository professorRepository, SubjectRepository subjectRepository){
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
    }

    public Optional<Professor> getprofessordetail(long id) {
        return professorRepository.findById(id);
    }

    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor assignSubjectToProfessor(Long professorId, Long subjectId) {
        Optional<Professor> professor = professorRepository.findById(professorId);
        Optional<Subject> subject = subjectRepository.findById(subjectId);

        return professor.flatMap(prof ->
                subject.map(sub -> {
                    sub.setProfessor(prof);
                    subjectRepository.save(sub);

                    prof.getSubjects().add(sub);
                    return prof;
                })).orElse(null);
    }
}
