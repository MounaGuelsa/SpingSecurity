package org.apache.maven.archetypes.labxpertproject.service.interfaces;

import java.util.List;

import org.apache.maven.archetypes.labxpertproject.DTOs.PatientDTO;

public interface IPatientService {
    PatientDTO addPatient(PatientDTO patientDto);

    List<PatientDTO> getAllPatients();

    PatientDTO getPatientById(Long patientId);

    PatientDTO updatePatient(PatientDTO patientDto);

    void deletePatient(Long patientId);

}
