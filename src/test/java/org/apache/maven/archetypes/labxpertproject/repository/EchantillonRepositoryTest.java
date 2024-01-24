//package org.apache.maven.archetypes.labxpertproject.repository;
//
//import org.apache.maven.archetypes.labxpertproject.LabXpertProjectApplication;
//import org.apache.maven.archetypes.labxpertproject.entitiy.enums.SexeType;
//import org.apache.maven.archetypes.labxpertproject.entitiy.model.Analyse;
//import org.apache.maven.archetypes.labxpertproject.entitiy.model.Echantillon;
//import org.apache.maven.archetypes.labxpertproject.entitiy.model.Patient;
//import org.apache.maven.archetypes.labxpertproject.entitiy.enums.StatutDanalyse;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//
//import java.time.LocalDate;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringJUnitConfig(LabXpertProjectApplication.class)
//@SpringBootTest
//class EchantillonRepositoryTest {
//
//    @Autowired
//    EchantillonRepository echantillonRepository;
//    @Autowired
//    PatientRepository patientRepository;
//    @Autowired
//    AnalyseRepository analyseRepository;
//
//    Echantillon testEchantillon;
//    Patient testPatient;
//    Analyse testAnalyse;
//
//    @BeforeEach
//    void setUp() {
//        // Create a Patient entity to test
//        testPatient = new Patient();
//        testPatient.setNom("Test Patient");
//        testPatient.setDateDeNaissance(LocalDate.now());
//        testPatient.setSexe(SexeType.HOMME);
//        testPatient.setAdresse("Test Address");
//        testPatient.setTelephone("1234567890");
//        testPatient = patientRepository.save(testPatient);
//
//        // Create an Echantillon entity to test
//        testEchantillon = new Echantillon();
//        testEchantillon.setDatePrelevement(LocalDate.now());
//        testEchantillon.setPatient(testPatient);
//        testEchantillon = echantillonRepository.save(testEchantillon);
//
//        // Create an Analyse entity to associate with the Echantillon entity
//        testAnalyse = new Analyse();
//        testAnalyse.setDateDebutAnalyse(LocalDate.now());
//        testAnalyse.setDateFinAnalyse(LocalDate.now().plusDays(7));
//        testAnalyse.setEtatAnalyse(StatutDanalyse.EN_ATTENTE);
//        testAnalyse.setCommentaire("Test Comment");
//        testAnalyse.setEchantillon(testEchantillon);
//        testAnalyse = analyseRepository.save(testAnalyse);
//
//
//
//    }
//
//    @Test
//    void testCreateEchantillon() {
//        Echantillon createdEchantillon = echantillonRepository.findById(testEchantillon.getEchantillonId()).orElse(null);
//
//        assertThat(createdEchantillon).isNotNull();
//        assertThat(createdEchantillon.getDatePrelevement()).isEqualTo(LocalDate.now());
//    }
//
//    @Test
//    void testUpdateEchantillon() {
//        Echantillon existingEchantillon = echantillonRepository.findById(testEchantillon.getEchantillonId()).orElse(null);
//        existingEchantillon.setDatePrelevement(LocalDate.now().minusDays(1));
//
//        echantillonRepository.save(existingEchantillon);
//
//        Echantillon updatedEchantillon = echantillonRepository.findById(existingEchantillon.getEchantillonId()).orElse(null);
//
//        assertThat(updatedEchantillon).isNotNull();
//        assertThat(updatedEchantillon.getDatePrelevement()).isEqualTo(LocalDate.now().minusDays(1));
//    }
//
//    @Test
//    void testFindEchantillonById() {
//        Echantillon foundEchantillon = echantillonRepository.findById(testEchantillon.getEchantillonId()).orElse(null);
//
//        assertThat(foundEchantillon).isNotNull();
//        assertThat(foundEchantillon.getDatePrelevement()).isEqualTo(LocalDate.now());
//    }
//
//
//    @Test
//    void testDeleteEchantillon() {
//        echantillonRepository.deleteById(testEchantillon.getEchantillonId());
//        Echantillon deletedEchantillon = echantillonRepository.findById(testEchantillon.getEchantillonId()).orElse(null);
//        assertThat(deletedEchantillon).isNull();
//    }
//
//    @AfterEach
//    void tearDown() {
//        Echantillon deletedEchantillon = echantillonRepository.findById(testEchantillon.getEchantillonId()).orElse(null);
//        if (deletedEchantillon != null) {
//            echantillonRepository.deleteById(testEchantillon.getEchantillonId());
//        } else {
//            System.out.println("Echantillon not found.");
//        }
//    }
//}
