package org.apache.maven.archetypes.labxpertproject.entitiy.model;

import lombok.Data;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.RoleDutilisateur;

import javax.persistence.*;

@Entity
@Data
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ryu")
    @SequenceGenerator(name = "ryu", sequenceName = "ryu", allocationSize = 1)
    private Long utilisateurId;

    @Column(name = "nomutilisateur")
    private String nomUtilisateur;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "roleDutilisateur")
    @Enumerated(EnumType.STRING)
    private RoleDutilisateur roleDutilisateur;

    @Column(name = "informationsPersonnalises")
    private String InformationsPersonalises;
}
