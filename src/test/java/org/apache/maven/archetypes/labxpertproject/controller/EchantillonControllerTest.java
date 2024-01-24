//package org.apache.maven.archetypes.labxpertproject.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.apache.maven.archetypes.labxpertproject.DTOs.EchantillonDTO;
//import org.apache.maven.archetypes.labxpertproject.DTOs.PatientDTO;
//import org.apache.maven.archetypes.labxpertproject.entitiy.enums.SexeType;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Collections;
//
//
//import org.apache.maven.archetypes.labxpertproject.entitiy.model.Analyse;
//import org.apache.maven.archetypes.labxpertproject.entitiy.model.Echantillon;
//import org.apache.maven.archetypes.labxpertproject.entitiy.model.Patient;
//import org.apache.maven.archetypes.labxpertproject.service.interfaces.IEchantillonService;
//import org.springframework.test.web.servlet.ResultActions;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@WebMvcTest(controllers = PatientController.class)
//@AutoConfigureMockMvc(addFilters = false)
//@ExtendWith(MockitoExtension.class)
//public class EchantillonControllerTest {
//    @MockBean
//    IEchantillonService echantillonService;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    EchantillonDTO echantillonDto;
//
////    @BeforeEach
////    public void init() {
////        Patient patient = new Patient();
////        List<Analyse> analyses = new ArrayList<>();
////        echantillonDto = new EchantillonDTO();
////        echantillonDto.setEchantillonId(1L);
////        echantillonDto.setPatientId(patient.getPatientId());
////        echantillonDto.setDatePrelevement(LocalDate.now());
////
////    }
//
//    @BeforeEach
//    public void init() {
//        echantillonDto = new EchantillonDTO();
//        echantillonDto.setEchantillonId(1L);
//        echantillonDto.setPatientId(1L);
//        echantillonDto.setDatePrelevement(LocalDate.now());
//        echantillonDto.toString();
//
//    }
//
//    @Test
//    public void test_getAllEchantillons() throws Exception {
//
//        System.out.println("=================== debut ====================");
//        System.out.println(echantillonDto);
//        System.out.println("=================== end ====================");
//
//        //        List<EchantillonDTO> echantillonDTOList = Arrays.asList(
////                new EchantillonDTO(),
////                new EchantillonDTO(),
////                new EchantillonDTO()
////        );
////        List<EchantillonDTO> singletonList = Collections.singletonList(echantillonDto);
//
//        Mockito.when(echantillonService.getAllEchantillons()).thenReturn(Collections.singletonList(echantillonDto));
//
//        ResultActions resultAction = mockMvc.perform(get("/api/echantillons")
//                .contentType(MediaType.APPLICATION_JSON));
//
//        resultAction.andExpect(status().isOk()).andDo(print());
//
//    }
//
//    @Test
//    public void test_getEchantillonById() throws Exception {
//
//    }
//
//    @Test
//    public void test_createEchantillon() throws Exception {
//
//       /* EchantillonDTO createEchantillon = echantillonService.createEchantillon(ArgumentMatchers.any());
//        given(createEchantillon).willAnswer((invocation -> invocation.getArgument(0)));
//
//        ResultActions resultAction = mockMvc.perform(post("/api/echantillons")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(echantillonDto)));
//
//        resultAction.andExpect(jsonPath("$.echantillonId", CoreMatchers.is(echantillonDto.getEchantillonId())))
//                .andExpect(jsonPath("$.patient", CoreMatchers.is(echantillonDto.getPatient())))
//                .andExpect(jsonPath("$.analyses", CoreMatchers.is(echantillonDto.getAnalyses())))
//                .andExpect(jsonPath("$.datePrelevement", CoreMatchers.is(echantillonDto.getDatePrelevement())))
//                .andExpect(status().isOk());*/
//    }
//
//    @Test
//    public void test_updateEchantillon() throws Exception {
//
//    }
//
//    @Test
//    public void test_deleteEchantillon() throws Exception {
//
//    }
//
//}
