package org.apache.maven.archetypes.labxpertproject.repository;

import org.apache.maven.archetypes.labxpertproject.LabXpertProjectApplication;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.StatutDanalyse;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.Analyse;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.Reactif;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(LabXpertProjectApplication.class)
@SpringBootTest
class ReactifRepositoryTest {

    @Autowired
    ReactifRepository reactifRepository;

    @Autowired
    AnalyseRepository analyseRepository;

    Reactif testReactif;
    Analyse testAnalyse;

    @BeforeEach
    void setUp() {
        // Create an Analyse entity to associate with the Reactif entity
        testAnalyse = new Analyse();
        testAnalyse.setDateDebutAnalyse(LocalDate.now());
        testAnalyse.setDateFinAnalyse(LocalDate.now().plusDays(7));
        testAnalyse.setEtatAnalyse(StatutDanalyse.EN_COURS_DANALYSE);
        testAnalyse.setCommentaire("Test Comment");
        testAnalyse = analyseRepository.save(testAnalyse);

        // Create a Reactif entity to test
        testReactif = new Reactif();
        testReactif.setNom("Test Reactif");
        testReactif.setDescription("Test Description");
        testReactif.setQuantite(10);
        testReactif.setDateDeExpiration("2024-12-31");
        testReactif.setFournisseur("Test Supplier");

        testReactif.setAnalyse(testAnalyse);
        testReactif = reactifRepository.save(testReactif);
    }

    @Test
    void testCreateReactif() {
        Reactif createdReactif = reactifRepository.findById(testReactif.getReactifId()).orElse(null);

        assertThat(createdReactif).isNotNull();
        assertThat(createdReactif.getNom()).isEqualTo("Test Reactif");
    }

    @Test
    void testUpdateReactif() {
        Reactif existingReactif = reactifRepository.findById(testReactif.getReactifId()).orElse(null);
        existingReactif.setNom("Updated Reactif");

        reactifRepository.save(existingReactif);

        Reactif updatedReactif = reactifRepository.findById(existingReactif.getReactifId()).orElse(null);

        assertThat(updatedReactif).isNotNull();
        assertThat(updatedReactif.getNom()).isEqualTo("Updated Reactif");
    }

    @Test
    void testFindReactifById() {
        Reactif foundReactif = reactifRepository.findById(testReactif.getReactifId()).orElse(null);

        assertThat(foundReactif).isNotNull();
        assertThat(foundReactif.getNom()).isEqualTo("Test Reactif");
    }

    @Test
    void testDeleteReactif() {
        reactifRepository.deleteById(testReactif.getReactifId());

        Reactif deletedReactif = reactifRepository.findById(testReactif.getReactifId()).orElse(null);
        assertThat(deletedReactif).isNull();
    }

    @AfterEach
    void tearDown() {
        Reactif reactiftobedeleted = reactifRepository.findById(testReactif.getReactifId()).orElse(null);
        if ( reactiftobedeleted != null) {
            reactifRepository.deleteById(testReactif.getReactifId());
        }

    }
}
