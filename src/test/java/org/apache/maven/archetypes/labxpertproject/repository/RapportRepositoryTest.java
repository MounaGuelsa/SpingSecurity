package org.apache.maven.archetypes.labxpertproject.repository;

import org.apache.maven.archetypes.labxpertproject.LabXpertProjectApplication;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.PeriodeDeRapport;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.TypeDeRapport;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.Rapport;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(LabXpertProjectApplication.class)
@SpringBootTest
class RapportTest {

    @Autowired
    RapportRepository rapportRepository;

    Rapport testRapport;

    @BeforeEach
    void setUp() {
        // Create a Rapport entity to test
        testRapport = new Rapport();
        testRapport.setTypeDeRapport(TypeDeRapport.PERFORMANCE_DU_LABORATOIRE);
        testRapport.setPeriodeDeRapport(PeriodeDeRapport.JOUR);
        testRapport.setStatistiques("Test Statistics");
        testRapport.setGraphiques("Test Graphs");
        testRapport = rapportRepository.save(testRapport);
    }

    @Test
    void testCreateRapport() {
        Rapport createdRapport = rapportRepository.findById(testRapport.getId()).orElse(null);

        assertThat(createdRapport).isNotNull();
        assertThat(createdRapport.getTypeDeRapport()).isEqualTo(TypeDeRapport.PERFORMANCE_DU_LABORATOIRE);
    }

    @Test
    void testUpdateRapport() {
        Rapport existingRapport = rapportRepository.findById(testRapport.getId()).orElse(null);
        existingRapport.setTypeDeRapport(TypeDeRapport.PERFORMANCE_DU_LABORATOIRE);

        rapportRepository.save(existingRapport);

        Rapport updatedRapport = rapportRepository.findById(existingRapport.getId()).orElse(null);

        assertThat(updatedRapport).isNotNull();
        assertThat(updatedRapport.getTypeDeRapport()).isEqualTo(TypeDeRapport.PERFORMANCE_DU_LABORATOIRE);
    }

    @Test
    void testFindRapportById() {
        Rapport foundRapport = rapportRepository.findById(testRapport.getId()).orElse(null);

        assertThat(foundRapport).isNotNull();
        assertThat(foundRapport.getTypeDeRapport()).isEqualTo(TypeDeRapport.PERFORMANCE_DU_LABORATOIRE);
    }

    @Test
    void testDeleteRapport() {
        rapportRepository.deleteById(testRapport.getId());

        Rapport deletedRapport = rapportRepository.findById(testRapport.getId()).orElse(null);
        assertThat(deletedRapport).isNull();
    }

    @AfterEach
    void tearDown() {
        if (rapportRepository.findById(testRapport.getId()).orElse(null) != null)
            rapportRepository.deleteById(testRapport.getId());
    }
}
