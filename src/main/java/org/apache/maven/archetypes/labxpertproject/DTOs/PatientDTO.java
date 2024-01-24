package org.apache.maven.archetypes.labxpertproject.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.SexeType;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Long patientId;

    @NotBlank(message = "Veuillez fournir un nom")
    @NotNull(message = "Le nom ne peut pas être null")
    @Size(min = 5, max = 20, message = "Le nom d'patient doit comporter entre 5 et 50 caractères")
    private String nom;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "La date de naissance ne peut pas être null")
    @PastOrPresent(message = "La date de naissance doit être dans le passé ou le présent")
    private LocalDate dateDeNaissance;

    @NotNull(message = "Le nom ne peut pas être null")
    private SexeType sexe;

    @NotBlank(message = "Veuillez fournir une Adresse")
    @NotNull(message = "Le nom ne peut pas être null")
    private String adresse;

    @NotNull(message = "Veuillez saisir un numero de telephone")
    @Pattern(regexp = "^\\+212-\\d{3}-\\d{3}-\\d{3}$",
            message = "Format de numéro de téléphone invalide ,veuillez respecter le format suivant ex:+212-000-000-000")
    private String telephone;

}