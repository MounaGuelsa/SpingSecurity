package org.apache.maven.archetypes.labxpertproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import liquibase.exception.LiquibaseException;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.RoleDutilisateur;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.Analyse;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.Utilisateur;
import org.apache.maven.archetypes.labxpertproject.repository.AnalyseRepository;
import org.apache.maven.archetypes.labxpertproject.repository.PatientRepository;
import org.apache.maven.archetypes.labxpertproject.repository.UtilisateurRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@EnableSwagger2
@EnableWebMvc

@SpringBootApplication
public class LabXpertProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabXpertProjectApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}