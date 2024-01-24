package org.apache.maven.archetypes.labxpertproject.DTOs;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class ReactifDTO {

    private Long reactifId;

    @NotBlank(message = "Name cannot be blank")
    private String nom;

    private String description;

    @Positive(message = "Quantity must be a positive value")
    private int quantite;

    @Future(message = "Expiration date must be in the future")
    private LocalDate dateDeExpiration;

    private String fournisseur;

    private Long analyseId;


}
