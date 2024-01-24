package org.apache.maven.archetypes.labxpertproject.service.interfaces;

import org.apache.maven.archetypes.labxpertproject.DTOs.UtilisateurDTO;



import java.util.*;

public interface IUtilisateurSerivce {

    UtilisateurDTO addUtilisateur(UtilisateurDTO utilisateurDTO);

    List<UtilisateurDTO> getAllUtilisateur();

    UtilisateurDTO getUtilisateurById(Long userId);

    UtilisateurDTO updateUtilisateur(UtilisateurDTO userDTO);

    void deleteUtilisateur(Long userId);
}



