package org.apache.maven.archetypes.labxpertproject.service.interfaces;

import org.apache.maven.archetypes.labxpertproject.DTOs.PlanificationDTO;

import java.util.List;

public interface IPlanificationService {

    PlanificationDTO addPlanification(PlanificationDTO planificationDTO);

    List<PlanificationDTO> getAllPlanification();

    PlanificationDTO getPlanificationById(Long id);

    PlanificationDTO updatePlanification(PlanificationDTO planificationDTO);

    void deletePlanification(Long id);
}
