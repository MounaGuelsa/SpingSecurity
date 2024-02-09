package org.apache.maven.archetypes.labxpertproject.Auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.RoleDutilisateur;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
    private RoleDutilisateur role;
}