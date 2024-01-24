package org.apache.maven.archetypes.labxpertproject.entitiy.model;

import lombok.Data;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.PeriodeDeRapport;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.TypeDeRapport;

import javax.persistence.*;

@Entity
@Data
@Table(name = "rapport")
public class Rapport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "your_sequence_name")
    @SequenceGenerator(name = "your_sequence_name", sequenceName = "your_sequence_name", allocationSize = 1)
    private Long id;

    @Column(name = "typeDeRapport")
    private TypeDeRapport typeDeRapport;

    @Column(name = "periodeDeRapport")
    private PeriodeDeRapport periodeDeRapport;

    @Column(name = "Statistiques")
    private String Statistiques;

    @Column(name = "Graphiques")
    private String Graphiques;
}
