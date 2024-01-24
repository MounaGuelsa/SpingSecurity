package org.apache.maven.archetypes.labxpertproject.entitiy.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "echantillon")
public class Echantillon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "echantillon_sequence")
    @SequenceGenerator(name = "echantillon_sequence", sequenceName ="echantillon_sequence", allocationSize = 1)
    @Column(name = "echantillon_id")
    private Long echantillonId;

    @ManyToOne (cascade = CascadeType.REMOVE)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany(mappedBy = "echantillon")
    private List<Analyse> analyses;

    @Column(name = "date_prelevement")
    private LocalDate datePrelevement;

}
