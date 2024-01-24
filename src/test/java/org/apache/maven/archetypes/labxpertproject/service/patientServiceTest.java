package org.apache.maven.archetypes.labxpertproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


import org.apache.maven.archetypes.labxpertproject.DTOs.PatientDTO;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.SexeType;
import org.apache.maven.archetypes.labxpertproject.repository.PatientRepository;
import org.apache.maven.archetypes.labxpertproject.service.interfaces.IPatientService;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class patientServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    IPatientService patientService;

    @Autowired
    private ModelMapper modelMapper;

    PatientDTO existingPatientDto;

    @BeforeEach
    public void init() {
        existingPatientDto = new PatientDTO();
        existingPatientDto.setNom("rachid");
        existingPatientDto.setDateDeNaissance(LocalDate.of(1988, 12, 1));
        existingPatientDto.setSexe(SexeType.HOMME);
        existingPatientDto.setAdresse("France");
    }

    @Test
    public void test_addPatient() {
        existingPatientDto.setTelephone("+212-000-000-001"); // unique telephone number
        PatientDTO resultDto = patientService.addPatient(existingPatientDto);
        assertNotNull(resultDto, "le patient n'existe pas ");
        assertEquals(resultDto.getNom(), existingPatientDto.getNom(), "fghfghf");
    }

    @Test
    public void test_getAllPatients() {
        List<PatientDTO> patientDtos = patientService.getAllPatients();
        assertNotNull(patientDtos, "list is empty");
    }

    @Test
    public void test_getPatientById() {
        // Create and save a new PatientDTO
        existingPatientDto.setTelephone("+212-000-000-004"); // unique telephone number
        PatientDTO newPatientDto = patientService.addPatient(existingPatientDto);

        // Get the patientId of the new PatientDTO
        long patientId = newPatientDto.getPatientId();

        // Now get the PatientDTO by id
        PatientDTO patientDTO = patientService.getPatientById(patientId);

        // Now this should not be null
        assertNotNull(patientDTO, "PatientDTO is null");

        // Continue with your test
        System.out.println(patientDTO.getNom());
    }

    @Test
    public void test_updatePatient() {
        long patientId = 3L;
        existingPatientDto.setPatientId(patientId);
        existingPatientDto.setNom("rachiddddd");
        existingPatientDto.setDateDeNaissance(LocalDate.of(1996, 12, 1));
        existingPatientDto.setSexe(SexeType.HOMME);
        existingPatientDto.setAdresse("France");
        existingPatientDto.setTelephone("+212-000-000-042"); // unique telephone number

        PatientDTO updatePatientDto = patientService.addPatient(existingPatientDto);


        updatePatientDto = patientService.updatePatient(updatePatientDto);
        assertNotEquals(existingPatientDto, updatePatientDto.getNom(), "la modification n'est pas éfectuer");
    }

    @Test
    public void test_deletePatient() {
        existingPatientDto.setTelephone("+212-000-000-003"); // unique telephone number

        PatientDTO newPatientDto = patientService.addPatient(existingPatientDto);

        Long patientId = newPatientDto.getPatientId();

        assertNotNull(patientService.getPatientById(patientId), "l'enregistrement Patient doivent exister avant la suppression ");

        patientService.deletePatient(patientId);

        assertNull(patientService.getPatientById(patientId), "l'enregistrement Patient ne doivent pas exister après la suppression");

    }
}
