package org.apache.maven.archetypes.labxpertproject.controller;

import org.apache.maven.archetypes.labxpertproject.DTOs.EchantillonDTO;
import org.apache.maven.archetypes.labxpertproject.service.interfaces.IEchantillonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/echantillons")
public class    EchantillonController {

    @Autowired
    private IEchantillonService echantillonService;

@GetMapping("{id}")
public ResponseEntity<EchantillonDTO> getEchantillonById(@PathVariable("id") Long echantillonId) {
    try {
        EchantillonDTO echantillonById = echantillonService.getEchantillonById(echantillonId);
        return ResponseEntity.ok(echantillonById);
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}
    @GetMapping
    public List<EchantillonDTO> getAllEchantillon(){
        List<EchantillonDTO> echantillons = echantillonService.getAllEchantillons();
      return echantillons;
    }

    @PostMapping
    public ResponseEntity<EchantillonDTO> createEchantillon(@Valid @RequestBody EchantillonDTO echantillonDTO) {
        EchantillonDTO createdEchantillon = echantillonService.createEchantillon(echantillonDTO);
        return new ResponseEntity<>(createdEchantillon, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EchantillonDTO> updateEchantillon(
            @PathVariable("id") Long echantillonId,
            @RequestBody EchantillonDTO updatedEchantillonDTO) {
        EchantillonDTO updatedEchantillon = echantillonService.updateEchantillon(echantillonId ,updatedEchantillonDTO);
        return new ResponseEntity<>(updatedEchantillon, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEchantillon(@PathVariable("id") Long echantillonId) {
        echantillonService.deleteEchantillon(echantillonId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
