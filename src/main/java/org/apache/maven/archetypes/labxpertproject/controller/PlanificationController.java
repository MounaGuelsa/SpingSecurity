package org.apache.maven.archetypes.labxpertproject.controller;

import org.apache.maven.archetypes.labxpertproject.DTOs.PlanificationDTO;
import org.apache.maven.archetypes.labxpertproject.service.interfaces.IPlanificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/planifications")
public class PlanificationController {

    @Autowired
    private IPlanificationService planificationService;

    @PostMapping
    public ResponseEntity<PlanificationDTO> addPlanification(@RequestBody @Valid PlanificationDTO planificationDTO) {
        PlanificationDTO addedPlanification = planificationService.addPlanification(planificationDTO);
        return new ResponseEntity<>(addedPlanification, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlanificationDTO>> getAllPlanifications() {
        List<PlanificationDTO> planifications = planificationService.getAllPlanification();
        return new ResponseEntity<>(planifications, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanificationDTO> getPlanificationById(@PathVariable Long id) {
        PlanificationDTO planification = planificationService.getPlanificationById(id);
        return new ResponseEntity<>(planification, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanificationDTO> updatePlanification(@PathVariable Long id, @RequestBody @Valid PlanificationDTO planificationDTO) {
        planificationDTO.setPlanificationId(id);
        PlanificationDTO updatedPlanification = planificationService.updatePlanification(planificationDTO);
        return new ResponseEntity<>(updatedPlanification, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlanification(@PathVariable Long id) {
        planificationService.deletePlanification(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
