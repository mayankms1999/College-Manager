package com.week3.mapping.Controller;

import com.week3.mapping.Entity.AdmissionRecord;
import com.week3.mapping.Service.AdmissionRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/AdmissionRecord")
public class AdmissionRecordController {
    private final AdmissionRecordService admissionRecordService;

    public AdmissionRecordController(AdmissionRecordService admissionRecordService){
        this.admissionRecordService = admissionRecordService;
    }


    @GetMapping("/{id}")
    public Optional<AdmissionRecord> getAdmissionRecordDetail(@PathVariable long id){
        return admissionRecordService.getAdmissionRecordDetail(id);
    }

    @PutMapping(path = "/{admissionRecordId}/student/{studentId}")
    public AdmissionRecord assignStudentToAdmissionRecord(@PathVariable Long admissionRecordId,
                                                      @PathVariable Long studentId) {
        return admissionRecordService.assignStudentToAdmissionRecord(admissionRecordId, studentId);
    }
    @PostMapping("/create")
    public Optional<AdmissionRecord> createAdmissionRecord(@RequestBody AdmissionRecord admissionRecord){
        return admissionRecordService.save(admissionRecord);
    }
}
