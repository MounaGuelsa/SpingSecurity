package org.apache.maven.archetypes.labxpertproject.entitiy.model;

import lombok.Data;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.SexeType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "patient")
public class Patient {

    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_sequence")
    @SequenceGenerator(name = "patient_sequence", sequenceName = "patient_sequence", allocationSize = 1)

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "nom")
    private String nom;

    @Column(name = "date_de_naissance")
    private LocalDate dateDeNaissance;

    @Column(name = "sexe")
    @Enumerated(EnumType.STRING)
    private SexeType sexe;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "telephone")
    private String telephone;


    @OneToMany(mappedBy = "patient" )
    private List<Echantillon> echantillons = new ArrayList<>();
}
