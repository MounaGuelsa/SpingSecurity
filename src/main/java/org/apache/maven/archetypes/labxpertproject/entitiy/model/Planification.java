package org.apache.maven.archetypes.labxpertproject.entitiy.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "planification")
public class Planification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planificationsequence")
    @SequenceGenerator(name = "planificationsequence", sequenceName = "planificationsequence", allocationSize = 1)
    @Column(name = "planification_id")
    private Long planificationId;

    @OneToMany(mappedBy = "planification" ,cascade = CascadeType.REMOVE)
    private List<Analyse> analyses = new ArrayList<>();

    @ManyToOne (cascade = CascadeType.REMOVE)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @Column(name = "DateDebutPlanification")
    private LocalDate dateDebutPlanification;

    @Column(name = "DateFinPlanification")
    private LocalDate dateFinPlanification;
}
