package org.apache.maven.archetypes.labxpertproject.controller;

import org.apache.maven.archetypes.labxpertproject.DTOs.UtilisateurDTO;
import org.apache.maven.archetypes.labxpertproject.service.interfaces.IUtilisateurSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UtilisateurController {

    @Autowired
    private IUtilisateurSerivce utilisateurService;

    @PostMapping
    public ResponseEntity<UtilisateurDTO> addUser(@RequestBody @Valid UtilisateurDTO utilisateurDTO) {
        UtilisateurDTO addedUser = utilisateurService.addUtilisateur(utilisateurDTO);
        return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UtilisateurDTO>> getAllUsers() {
        List<UtilisateurDTO> users = utilisateurService.getAllUtilisateur();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> getUserById(@PathVariable Long id) {
        UtilisateurDTO user = utilisateurService.getUtilisateurById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UtilisateurDTO utilisateurDTO) {
        utilisateurDTO.setUtilisateurId(id);
        UtilisateurDTO updatedUser = utilisateurService.updateUtilisateur(utilisateurDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }



}
