package com.week3.mapping.Service;

import com.week3.mapping.Entity.AdmissionRecord;
import com.week3.mapping.Entity.Student;
import com.week3.mapping.Entity.Professor;
import com.week3.mapping.Repository.AdmissionRecordRepository;
import com.week3.mapping.Repository.ProfessorRepository;
import com.week3.mapping.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdmissionRecordService {

    private final AdmissionRecordRepository admissionRecordRepository;
    private final StudentRepository studentRepository;
    public AdmissionRecordService(AdmissionRecordRepository admissionRecordRepository, StudentRepository studentRepository){
        this.admissionRecordRepository = admissionRecordRepository;
        this.studentRepository = studentRepository;
    }


    public Optional<AdmissionRecord> getAdmissionRecordDetail(long id) {
        return admissionRecordRepository.findById(id);
    }

    public Optional<AdmissionRecord> save(AdmissionRecord admissionRecord) {
        return Optional.of(admissionRecordRepository.save(admissionRecord));
    }

    public AdmissionRecord assignStudentToAdmissionRecord(Long admissionRecordId, Long studentId) {
        Optional<AdmissionRecord> admissionRecord = admissionRecordRepository.findById(admissionRecordId);
        Optional<Student> student = studentRepository.findById(studentId);

        return admissionRecord.flatMap(record ->
                student.map(stud -> {
                    record.setStudent(stud);
                    return admissionRecordRepository.save(record);
                })).orElse(null);
    }
}
