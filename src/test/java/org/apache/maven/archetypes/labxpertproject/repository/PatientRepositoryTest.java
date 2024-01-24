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
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringJUnitConfig(LabXpertProjectApplication.class)
//@SpringBootTest
//class PatientRepositoryTest {
//
//    @Autowired
//    PatientRepository patientRepository;
//    @Autowired
//    EchantillonRepository echantillonRepository;
//    @Autowired
//    AnalyseRepository analyseRepository;
//
//    Patient testPatient;
//    Echantillon testEchantillon;
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
//        // Create an Echantillon entity to associate with the Patient entity
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
//    }
//
//    @Test
//    void testCreatePatient() {
//        Patient createdPatient = patientRepository.findById(testPatient.getPatientId()).orElse(null);
//
//        assertThat(createdPatient).isNotNull();
//        assertThat(createdPatient.getNom()).isEqualTo("Test Patient");
//    }
//
//    @Test
//    void testUpdatePatient() {
//        Patient existingPatient = patientRepository.findById(testPatient.getPatientId()).orElse(null);
//        existingPatient.setNom("Updated Patient");
//
//        patientRepository.save(existingPatient);
//
//        Patient updatedPatient = patientRepository.findById(existingPatient.getPatientId()).orElse(null);
//
//        assertThat(updatedPatient).isNotNull();
//        assertThat(updatedPatient.getNom()).isEqualTo("Updated Patient");
//    }
//
//    @Test
//    void testFindPatientById() {
//        Patient foundPatient = patientRepository.findById(testPatient.getPatientId()).orElse(null);
//
//        assertThat(foundPatient).isNotNull();
//        assertThat(foundPatient.getNom()).isEqualTo("Test Patient");
//    }
//
//    @Test
//    void testDeletePatient() {
//        patientRepository.deleteById(testPatient.getPatientId());
//        Patient deletedPatient = patientRepository.findById(testPatient.getPatientId()).orElse(null);
//        assertThat(deletedPatient).isNull();
//    }
//
//    @AfterEach
//    void tearDown() {
//        Patient deletedPatient = patientRepository.findById(testPatient.getPatientId()).orElse(null);
//        if (deletedPatient != null) {
//            patientRepository.deleteById(testPatient.getPatientId());
//        } else {
//            System.out.println("Patient not found.");
//        }
//    }
//}
