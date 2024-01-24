package org.apache.maven.archetypes.labxpertproject.controller;

import org.apache.maven.archetypes.labxpertproject.DTOs.PlanificationDTO;
import org.apache.maven.archetypes.labxpertproject.DTOs.ReactifDTO;
import org.apache.maven.archetypes.labxpertproject.service.interfaces.IReactifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reactifs")
public class ReactifController {

    @Autowired
    private IReactifService reactifService;

    @PostMapping
    public ResponseEntity<ReactifDTO> addReactif(@RequestBody @Valid ReactifDTO reactifDTO) {
        ReactifDTO addedReactif = reactifService.addReactif(reactifDTO);
        return new ResponseEntity<>(addedReactif, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReactifDTO>> getAllReactifs() {
        List<ReactifDTO> reactifs = reactifService.getAllReactifs();
        return new ResponseEntity<>(reactifs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReactifDTO> getReactifById(@PathVariable Long id) {
        ReactifDTO reactif = reactifService.getReactifById(id);
        if (reactif != null) {
            return new ResponseEntity<>(reactif, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ReactifDTO> updateReactif(@PathVariable Long id, @RequestBody @Valid ReactifDTO reactifDTO) {
        reactifDTO.setReactifId(id);
        ReactifDTO updatedReactif = reactifService.updateReactif(reactifDTO);
        return new ResponseEntity<>(updatedReactif, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReactif(@PathVariable Long id) {
        reactifService.deleteReactif(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
