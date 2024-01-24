//package org.apache.maven.archetypes.labxpertproject.repository;
//
//import org.apache.maven.archetypes.labxpertproject.LabXpertProjectApplication;
//import org.apache.maven.archetypes.labxpertproject.entitiy.enums.RoleDutilisateur;
//import org.apache.maven.archetypes.labxpertproject.entitiy.model.Utilisateur;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@SpringJUnitConfig(LabXpertProjectApplication.class)
//@SpringBootTest
//class UtilisateurRepositoryTest {
//
//    @Autowired
//    private UtilisateurRepository utilisateurRepository;
//
//    private Utilisateur testUtilisateur;
//
//    @BeforeEach
//    void setUp() {
//        testUtilisateur = new Utilisateur();
//        testUtilisateur.setNomUtilisateur("testUser");
//        testUtilisateur.setEmail("test@example.com");
//        testUtilisateur.setPassword("password123");
//        testUtilisateur.setRoleDutilisateur(RoleDutilisateur.TECHNICIEN);
//        testUtilisateur.setInformationsPersonalises("Test user for testing");
//
//        testUtilisateur = utilisateurRepository.save(testUtilisateur);
//    }
//
//    @Test
//    void testCreateUser() {
//
//        Utilisateur createdUser = utilisateurRepository.findByNomUtilisateur(testUtilisateur.getNomUtilisateur());
//
//        assertThat(createdUser).isNotNull();
//        assertThat(createdUser.getEmail()).isEqualTo("test@example.com");
//    }
//
//    @Test
//    void testUpdateUser() {
//        Utilisateur existingUser = utilisateurRepository.findByNomUtilisateur(testUtilisateur.getNomUtilisateur());
//        existingUser.setInformationsPersonalises("Updated information");
//
//        utilisateurRepository.save(existingUser);
//
//        Utilisateur updatedUser = utilisateurRepository.findByNomUtilisateur(existingUser.getNomUtilisateur());
//
//        assertThat(updatedUser).isNotNull();
//        assertThat(updatedUser.getInformationsPersonalises()).isEqualTo("Updated information");
//    }
//
//    @Test
//    void testFindUserByUsername() {
//        Utilisateur foundUser = utilisateurRepository.findByNomUtilisateur(testUtilisateur.getNomUtilisateur());
//
//        assertThat(foundUser).isNotNull();
//        assertThat(foundUser.getNomUtilisateur()).isEqualTo("testUser");
//    }
//
//    @Test
//    void testDeleteUser() {
//        utilisateurRepository.deleteById(utilisateurRepository.findByNomUtilisateur(testUtilisateur.getNomUtilisateur()).getUtilisateurId());
//        Utilisateur deletedUser = utilisateurRepository.findByNomUtilisateur("testUser");
//        assertThat(deletedUser).isNull();
//    }
//
//
//    @AfterEach
//    void tearDown() {
//        Utilisateur userToDelete = utilisateurRepository.findByNomUtilisateur(testUtilisateur.getNomUtilisateur());
//        if (userToDelete != null) {
//            utilisateurRepository.deleteById(utilisateurRepository.findByNomUtilisateur("testUser").getUtilisateurId());
//        } else {
//            System.out.println("User not found.");
//        }
//    }
//}
