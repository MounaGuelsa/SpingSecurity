package org.apache.maven.archetypes.labxpertproject.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.RoleDutilisateur;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDTO {

    @Positive(message = "Utilisateur id must be positive")
    private Long utilisateurId;

    @NotBlank(message = "Nom utilisateur is required")
    private String nomUtilisateur;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must have at least 8 characters, 1 uppercase, 1 lowercase, 1 digit, and 1 special character"
    )
    private String password;


    @NotNull(message = "Role utilisateur is required")
    private RoleDutilisateur roleDutilisateur;


    @NotBlank(message = "Informations personalises is required")
    private String InformationsPersonalises;

    public UtilisateurDTO(String johnDoe, String mail, String s, RoleDutilisateur roleDutilisateur, String someinformation) {
    }
}

