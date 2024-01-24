package org.apache.maven.archetypes.labxpertproject.entitiy.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "SousAnalyseMesures")
public class SousAnalyseMesures {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SousAnalyseMesuresSequence")
    @SequenceGenerator(name = "SousAnalyseMesuresSequence", sequenceName = "SousAnalyseMesuresSequence", allocationSize = 1)
    @Column(name = "sousAnalyseMesuresId")  // Match the column name
    private Long sousAnalyseMesuresId;

    @Column(name = "analyse_name")  // Match the column name
    private String analyseName;

    @Column(name = "min")
    private double min;

    @Column(name = "max")
    private double max;

    @Column(name = "unite")
    private String unite;

    @OneToOne(mappedBy = "sousAnalyseMesures", cascade = CascadeType.ALL)
    private SousAnalyse sousAnalyse;
}
