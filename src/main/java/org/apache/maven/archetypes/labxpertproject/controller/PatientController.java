package org.apache.maven.archetypes.labxpertproject.controller;

import org.apache.maven.archetypes.labxpertproject.DTOs.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

import org.apache.maven.archetypes.labxpertproject.service.interfaces.IPatientService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @PostMapping
    public ResponseEntity<PatientDTO> addPatient(@RequestBody @Valid PatientDTO patientDto) {
        PatientDTO savedPatient = patientService.addPatient(patientDto);
        return new ResponseEntity<>(savedPatient, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients =patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable("id") Long patientId) {
        PatientDTO savedPatient = patientService.getPatientById(patientId);
        return new ResponseEntity<>(savedPatient, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable("id") Long patientId, @RequestBody PatientDTO patientDto) {
        patientDto.setPatientId(patientId);
        PatientDTO updatePatient = patientService.updatePatient(patientDto);
         return new ResponseEntity<>( updatePatient, HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePateint(@PathVariable("id") Long patientId) {
        patientService.deletePatient(patientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
