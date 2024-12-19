package com.week3.mapping.Controller;


import com.week3.mapping.Entity.AdmissionRecord;
import com.week3.mapping.Entity.Professor;
import com.week3.mapping.Entity.Subject;
import com.week3.mapping.Service.ProfessorService;
import com.week3.mapping.Service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/Professor")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService){
        this.professorService = professorService;
    }


    @GetMapping("/{id}")
    public Optional<Professor> getprofessordetail(@PathVariable long id){
        return professorService.getprofessordetail(id);
    }
    @PutMapping(path = "/{professorId}/subject/{subjectId}")
    public Professor assignSubjectToProfessor(@PathVariable Long professorId,
                                                          @PathVariable Long subjectId) {
        return professorService.assignSubjectToProfessor(professorId, subjectId);
    }
    @PostMapping("/create")
    public Professor createProfessor(@RequestBody Professor professor){
        return professorService.save(professor);
    }
}
