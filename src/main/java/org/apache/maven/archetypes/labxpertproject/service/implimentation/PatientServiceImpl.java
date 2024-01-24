package org.apache.maven.archetypes.labxpertproject.service.implimentation;

import org.apache.maven.archetypes.labxpertproject.DTOs.PatientDTO;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.Patient;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.Utilisateur;
import org.apache.maven.archetypes.labxpertproject.repository.PatientRepository;
import org.apache.maven.archetypes.labxpertproject.service.interfaces.IPatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements IPatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public PatientDTO addPatient(PatientDTO patientDto) {
        try {
            Patient patient = convertToEntity(patientDto);
            patient = patientRepository.save(patient);
            return convertToDTO(patient);

        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Ce numéro de téléphone est déjà utilisé", e);

        } catch (Exception e) {
            throw new RuntimeException("Error adding Patient", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDTO> getAllPatients() {
        try {
            List<Patient> patients = patientRepository.findAll();
            return patients.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error getting all patient ", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO getPatientById(Long patientId) {
        try {
            Optional<Patient> patient = patientRepository.findById(patientId);
            return patient.map(this::convertToDTO).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error getting patient by ID", e);
        }
    }

    @Override
    @Transactional
    public PatientDTO updatePatient(PatientDTO patientDto) {
        try {
            Optional<Patient> patient = patientRepository.findById(patientDto.getPatientId());
            if (patient.isPresent()) {
                modelMapper.map(patientDto, patient.get());
                patientRepository.save(patient.get());
                return convertToDTO(patient.get());
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error updating patient", e);
        }
    }

    @Override
    @Transactional
    public void deletePatient(Long patientId) {
        try {
            patientRepository.deleteById(patientId);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting patient", e);
        }
    }

    public PatientDTO convertToDTO(Patient patient) {
        return modelMapper.map(patient, PatientDTO.class);
    }

    public Patient convertToEntity(PatientDTO patientDTO) {
        return modelMapper.map(patientDTO, Patient.class);
    }

}
