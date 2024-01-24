package org.apache.maven.archetypes.labxpertproject.DTOs;

import lombok.Data;
import lombok.NonNull;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.Analyse;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.Utilisateur;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class PlanificationDTO {

    @Positive(message = "Planification ID must be a positive number")
    private Long planificationId;

    @Valid
    @NonNull
    private List<Analyse> analyses = new ArrayList<>();

    @Valid
    @NotNull(message = "Utilisateur must not be null")
    private Utilisateur utilisateur;

    @Future(message = "Start date must be in the future")
    @NotNull(message = "Start date must not be null")
    private LocalDate dateDebutPlanification;

    @Future(message = "End date must be in the future")
    @NotNull(message = "End date must not be null")
    private LocalDate dateFinPlanification;
}
