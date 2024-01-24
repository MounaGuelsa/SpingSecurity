package org.apache.maven.archetypes.labxpertproject.repository;

import org.apache.maven.archetypes.labxpertproject.LabXpertProjectApplication;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.RoleDutilisateur;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.*;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.StatutDanalyse;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;


@SpringJUnitConfig(LabXpertProjectApplication.class)
@SpringBootTest
class AnalyseRepositoryTest {

    @Autowired
    AnalyseRepository analyseRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    PlanificationRepository PlanificationRepository;
    @Autowired
    PatientRepository PatientRepository;
    @Autowired
    EchantillonRepository echantillonRepository;

    Analyse testAnalyse;
    Utilisateur testUtilisateur;
    Planification testPlanification;
    Echantillon testEchantillon;
    Patient testPatient;

    @BeforeEach
    void setUp() {
        // Create a Analyse entity to test
        testAnalyse = new Analyse();
        testAnalyse.setDateDebutAnalyse(LocalDate.now());
        testAnalyse.setDateFinAnalyse(LocalDate.now().plusDays(7));
        testAnalyse.setEtatAnalyse(StatutDanalyse.EN_ATTENTE);
        testAnalyse.setCommentaire("Test Comment");


        // Create a Utilisateur entity to associate with the Analyse entity
        testUtilisateur = new Utilisateur();
        testUtilisateur.setNomUtilisateur("testUser");
        testUtilisateur.setEmail("test@example.com");
        testUtilisateur.setPassword("password123");
        testUtilisateur.setRoleDutilisateur(RoleDutilisateur.TECHNICIEN);
        testUtilisateur = utilisateurRepository.save(testUtilisateur);



        // Create a Planification entity to associate with the Analyse entity
        testPlanification = new Planification();
        testPlanification.setDateDebutPlanification(LocalDate.now());
        testPlanification.setDateFinPlanification(LocalDate.now().plusDays(14));
        //todo:testPlanification.setUtilisateur(testUtilisateur);
        testPlanification = PlanificationRepository.save(testPlanification);



        // Create a Echantillon entity to associate with the Analyse entity
        testEchantillon = new Echantillon();
        testEchantillon = echantillonRepository.save(testEchantillon);



        // Create a Patient entity to associate with the Analyse entity
//        testPatient = new Patient();
//        testPatient = PatientRepository.save(testPatient);


        testAnalyse.setUtilisateur(testUtilisateur);
        testAnalyse.setPlanification(testPlanification);
        testAnalyse.setEchantillon(testEchantillon);
//        testAnalyse.setPatient(testPatient);
        testAnalyse = analyseRepository.save(testAnalyse);
    }

    @Test
    void testCreateAnalyse() {
        Analyse createdAnalyse = analyseRepository.findById(testAnalyse.getAnalyseId()).orElse(null);

        assertThat(createdAnalyse).isNotNull();
    }

    @Test
    void testUpdateAnalyse() {
        Analyse existingAnalyse = analyseRepository.findById(testAnalyse.getAnalyseId()).orElse(null);
        existingAnalyse.setCommentaire("Updated Comment");

        analyseRepository.save(existingAnalyse);

        Analyse updatedAnalyse = analyseRepository.findById(existingAnalyse.getAnalyseId()).orElse(null);

        assertThat(updatedAnalyse).isNotNull();
        assertThat(updatedAnalyse.getCommentaire()).isEqualTo("Updated Comment");
    }

    @Test
    void testFindAnalyseById() {
        Analyse foundAnalyse = analyseRepository.findById(testAnalyse.getAnalyseId()).orElse(null);

        assertThat(foundAnalyse).isNotNull();
        assertThat(foundAnalyse.getDateDebutAnalyse()).isEqualTo(LocalDate.now());
    }

    @Test
    void testDeleteAnalyse() {
        analyseRepository.deleteById(testAnalyse.getAnalyseId());
        Analyse deletedAnalyse = analyseRepository.findById(testAnalyse.getAnalyseId()).orElse(null);
        assertThat(deletedAnalyse).isNull();

    }



    @AfterEach
    void tearDown() {
        Analyse deletedAnalyse = analyseRepository.findById(testAnalyse.getAnalyseId()).orElse(null);
        if (deletedAnalyse != null) {
            analyseRepository.deleteById(testAnalyse.getAnalyseId());

        }else {
            System.out.println("Analyse not found.");
        }
    }
}
