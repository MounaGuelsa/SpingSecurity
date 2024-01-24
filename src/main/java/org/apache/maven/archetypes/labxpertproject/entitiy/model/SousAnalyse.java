package org.apache.maven.archetypes.labxpertproject.entitiy.model;

import lombok.Data;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.StatutDeResultat;

import javax.persistence.*;

@Entity
@Data
@Table(name = "SousAnalyse")
public class SousAnalyse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SousAnalyseSequence")
    @SequenceGenerator(name = "SousAnalyseSequence", sequenceName = "SousAnalyseSequence", allocationSize = 1)
    private Long sousAnalyseId;

    @Column(name = "valeur")
    private double valeur;

    @Enumerated(EnumType.STRING)
    @Column(name = "statutDeResultat")
    private StatutDeResultat statutDeResultat;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "analyse_id")
    private Analyse analyse;

    @OneToOne
    @JoinColumn(name = "sousAnalyseMesures_id")
    private SousAnalyseMesures sousAnalyseMesures;

}

