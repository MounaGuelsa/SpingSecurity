//package org.apache.maven.archetypes.labxpertproject.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.maven.archetypes.labxpertproject.DTOs.UtilisateurDTO;
//import org.apache.maven.archetypes.labxpertproject.service.interfaces.IUtilisateurSerivce;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.apache.maven.archetypes.labxpertproject.entitiy.enums.RoleDutilisateur.TECHNICIEN;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.mockito.BDDMockito.given;
//
//
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(UtilisateurController.class)
//
//public class UtilisateurControllerTest {
//
//    @MockBean
//    private IUtilisateurSerivce utilisateurService;
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Test
//    public void CreateUserTest() throws Exception {
//        // Arrange
//        UtilisateurDTO utilisateurDTO = new UtilisateurDTO("john_doe", "john.doe@example.co", "P@ssw0rd", TECHNICIEN, "Someinformation");
//        when(utilisateurService.addUtilisateur(utilisateurDTO)).thenReturn(utilisateurDTO);
//
//        // Act
//        ResultActions resultActions = mockMvc.perform(post("/api/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(utilisateurDTO)));
//
//        // Assert
//        resultActions.andExpect(status().isCreated());
//    }
//}
