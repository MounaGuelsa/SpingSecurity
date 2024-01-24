package org.apache.maven.archetypes.labxpertproject.controller;

import org.apache.maven.archetypes.labxpertproject.DTOs.AnalyseDTO;
import org.apache.maven.archetypes.labxpertproject.service.interfaces.IAnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/analyses")
public class AnalyseController {

    @Autowired
    private IAnalyseService analyseService;


    @PostMapping
    public ResponseEntity<?> addAnalyse(@RequestBody @Valid AnalyseDTO analyseDTO) {
        try{ AnalyseDTO addedAnalyse = analyseService.addAnalyse(analyseDTO);
            return new ResponseEntity<>(addedAnalyse, HttpStatus.CREATED);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<AnalyseDTO>> getAllAnalyses() {
        List<AnalyseDTO> analyses = analyseService.getAllAnalyses();
        return new ResponseEntity<>(analyses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalyseDTO> getAnalyseById(@PathVariable Long id) {
        AnalyseDTO analyse = analyseService.getAnalyseById(id);
        return new ResponseEntity<>(analyse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnalyseDTO> updateAnalyse(@PathVariable Long id, @RequestBody @Valid AnalyseDTO analyseDTO) {
        analyseDTO.setAnalyseId(id);
        AnalyseDTO updatedAnalyse = analyseService.updateAnalyse(id, analyseDTO);
        return new ResponseEntity<>(updatedAnalyse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnalyse(@PathVariable Long id) {
        analyseService.deleteAnalyse(id);
        return new ResponseEntity<>("Analyse deleted successfully", HttpStatus.OK);
    }
}
