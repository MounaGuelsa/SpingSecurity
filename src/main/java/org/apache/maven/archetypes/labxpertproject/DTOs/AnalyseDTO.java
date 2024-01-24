package org.apache.maven.archetypes.labxpertproject.DTOs;
import lombok.Data;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.AnalyseType;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.StatutDanalyse;

import java.time.LocalDate;
import java.util.List;

@Data
public class AnalyseDTO {

    private Long analyseId;

    private Long utilisateurId;

    private UtilisateurDTO utilisateur;

    private LocalDate dateDebutAnalyse;

    private LocalDate dateFinAnalyse;

    private List<SousAnalyseDTO> sousAnalyses;

    private AnalyseType analyseType;

    private StatutDanalyse etatAnalyse;

    private String commentaire;

    private Long planificationId;

    private Long echantillonId;

    private List<Long> reactifsIds;
}
