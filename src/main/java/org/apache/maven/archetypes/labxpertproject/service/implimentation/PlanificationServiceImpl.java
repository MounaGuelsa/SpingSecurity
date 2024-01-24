package org.apache.maven.archetypes.labxpertproject.service.implimentation;

import org.apache.maven.archetypes.labxpertproject.DTOs.PlanificationDTO;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.StatutDanalyse;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.Analyse;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.Planification;
import org.apache.maven.archetypes.labxpertproject.repository.PlanificationRepository;
import org.apache.maven.archetypes.labxpertproject.repository.AnalyseRepository;
import org.apache.maven.archetypes.labxpertproject.service.interfaces.IPlanificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanificationServiceImpl implements IPlanificationService {

    @Autowired
    private PlanificationRepository planificationRepository;

    @Autowired
    private AnalyseRepository analyseRepository;

    @Autowired
    private ModelMapper modelMapper;


    public PlanificationDTO convertToDTO(Planification planification) {
        return modelMapper.map(planification, PlanificationDTO.class);
    }


    public Planification convertToEntity(PlanificationDTO planificationDTO) {
        return modelMapper.map(planificationDTO, Planification.class);
    }


    @Override
    @Transactional
    public PlanificationDTO addPlanification(PlanificationDTO planificationDTO) {
        try {
            Planification planification = convertToEntity(planificationDTO);
            planification = planificationRepository.save(planification);
            System.out.println("Planification added successfully service");
            return convertToDTO(planification);
        } catch (Exception e) {
            throw new RuntimeException("Error adding planification", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlanificationDTO> getAllPlanification() {
        try {
            List<Planification> planifications = planificationRepository.findAll();
            return planifications.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error getting all planifications", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PlanificationDTO getPlanificationById(Long id) {
        try {
            Planification planification = planificationRepository.findById(id).orElse(null);
            return convertToDTO(planification);
        } catch (Exception e) {
            throw new RuntimeException("Error getting planification by id", e);
        }
    }

    @Override
    @Transactional
    public PlanificationDTO updatePlanification(PlanificationDTO planificationDTO) {
        try {
            Planification existingPlanification = planificationRepository.findById(planificationDTO.getPlanificationId()).orElse(null);
            if (existingPlanification != null) {
                modelMapper.map(planificationDTO, existingPlanification);
                planificationRepository.save(existingPlanification);
                return convertToDTO(existingPlanification);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error updating planification", e);
        }
    }

    @Override
    @Transactional
    public void deletePlanification(Long id) {
        try {
            Optional<Planification> planificationOptional = planificationRepository.findById(id);
            planificationOptional.ifPresent(planification -> {

                planification.setUtilisateur(null);
                planificationRepository.save(planification);

                planification.getAnalyses().forEach(analyse -> analyseRepository.deleteById(analyse.getAnalyseId()));

                planificationRepository.deleteById(id);
            });
        } catch (Exception e) {

            throw new RuntimeException("Error deleting planification", e);
        }
    }


}
