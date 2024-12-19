package com.week3.mapping.Controller;

import com.week3.mapping.Entity.Student;
import com.week3.mapping.Entity.Subject;
import com.week3.mapping.Service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping(path = "/Subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @GetMapping("/{id}")
    public Optional<Subject> getSubjectDetail(@PathVariable long id){
        return subjectService.getSubjectdetail(id);
    }
    @PutMapping(path = "/student/{subjectId}/subject/{studentId}")
    public Optional<Subject> assignStudentToSubject(@PathVariable Long studentId,
                                                      @PathVariable Long subjectId) {
        return Optional.ofNullable(subjectService.assignStudentToSubject(studentId, Collections.singletonList(subjectId)));
    }
    @PostMapping("/create")
    public Optional<Subject> createSubject(@RequestBody Subject subject){
        return subjectService.save(subject);
    }

}
