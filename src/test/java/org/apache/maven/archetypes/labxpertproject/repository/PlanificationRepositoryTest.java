package org.apache.maven.archetypes.labxpertproject.repository;

import org.apache.maven.archetypes.labxpertproject.LabXpertProjectApplication;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.RoleDutilisateur;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.Analyse;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.Planification;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.StatutDanalyse;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.Utilisateur;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(LabXpertProjectApplication.class)
@SpringBootTest
class PlanificationRepositoryTest {

    @Autowired
    PlanificationRepository planificationRepository;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    AnalyseRepository analyseRepository;

    Planification testPlanification;
    Utilisateur testUtilisateur;
    Analyse testAnalyse;

    @BeforeEach
    void setUp() {
        // Create a Utilisateur entity to test
        testUtilisateur = new Utilisateur();
        testUtilisateur.setNomUtilisateur("testUser");
        testUtilisateur.setEmail("test@example.com");
        testUtilisateur.setPassword("password123");
        testUtilisateur.setRoleDutilisateur(RoleDutilisateur.TECHNICIEN);
        testUtilisateur = utilisateurRepository.save(testUtilisateur);

        // Create an Analyse entity to associate with the Planification entity
        testAnalyse = new Analyse();
        testAnalyse.setDateDebutAnalyse(LocalDate.now());
        testAnalyse.setDateFinAnalyse(LocalDate.now().plusDays(7));
        testAnalyse.setEtatAnalyse(StatutDanalyse.EN_ATTENTE);
        testAnalyse.setCommentaire("Test Comment");
        testAnalyse = analyseRepository.save(testAnalyse);

        // Create a Planification entity to test
        testPlanification = new Planification();
        testPlanification.setDateDebutPlanification(LocalDate.now());
        testPlanification.setDateFinPlanification(LocalDate.now().plusDays(14));
        testPlanification.setUtilisateur(testUtilisateur);
        testPlanification.getAnalyses().add(testAnalyse);
        testPlanification = planificationRepository.save(testPlanification);
    }

    @Test
    void testCreatePlanification() {
        Planification createdPlanification = planificationRepository.findById(testPlanification.getPlanificationId()).orElse(null);

        assertThat(createdPlanification).isNotNull();
        assertThat(createdPlanification.getDateDebutPlanification()).isEqualTo(LocalDate.now());
    }

    @Test
    void testUpdatePlanification() {
        Planification existingPlanification = planificationRepository.findById(testPlanification.getPlanificationId()).orElse(null);
        existingPlanification.setDateDebutPlanification(LocalDate.now().minusDays(1));

        planificationRepository.save(existingPlanification);

        Planification updatedPlanification = planificationRepository.findById(existingPlanification.getPlanificationId()).orElse(null);

        assertThat(updatedPlanification).isNotNull();
        assertThat(updatedPlanification.getDateDebutPlanification()).isEqualTo(LocalDate.now().minusDays(1));
    }

    @Test
    void testFindPlanificationById() {
        Planification foundPlanification = planificationRepository.findById(testPlanification.getPlanificationId()).orElse(null);

        assertThat(foundPlanification).isNotNull();
        assertThat(foundPlanification.getDateDebutPlanification()).isEqualTo(LocalDate.now());
    }

    @Test
    void testDeletePlanification() {
        analyseRepository.deleteById(testAnalyse.getAnalyseId());
        planificationRepository.deleteById(testPlanification.getPlanificationId());

        Planification deletedPlanification = planificationRepository.findById(testPlanification.getPlanificationId()).orElse(null);
        assertThat(deletedPlanification).isNull();
    }

    @AfterEach
    void tearDown() {
        if (planificationRepository.findById(testPlanification.getPlanificationId()).orElse(null) != null) {
            analyseRepository.deleteById(testAnalyse.getAnalyseId());
            planificationRepository.deleteById(testPlanification.getPlanificationId());
        }
    }

}