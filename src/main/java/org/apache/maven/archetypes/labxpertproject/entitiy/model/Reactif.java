package org.apache.maven.archetypes.labxpertproject.entitiy.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "reactif")
public class Reactif {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reactif_name")
    @SequenceGenerator(name = "reactif_name", sequenceName = "reactif_name", allocationSize = 1)
    @Column(name = "reactif_id")
    private Long reactifId;

    @Column(name = "nom")
    private String nom;

    @Column(name = "Description")
    private String description;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "dateDeexpiration")
    private String dateDeExpiration;

    @Column(name = "fournisseur")
    private String fournisseur;

    @ManyToOne (cascade = CascadeType.REMOVE)
    @JoinColumn(name = "analyse_id")
    private Analyse analyse;


}
