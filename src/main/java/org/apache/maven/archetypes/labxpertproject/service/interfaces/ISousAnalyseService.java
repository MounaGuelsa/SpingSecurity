package org.apache.maven.archetypes.labxpertproject.service.interfaces;

import org.apache.maven.archetypes.labxpertproject.DTOs.CombinedSousAnalyseDTO;
import org.apache.maven.archetypes.labxpertproject.DTOs.SousAnalyseDTO;
import org.apache.maven.archetypes.labxpertproject.DTOs.SousAnalyseMesuresDTO;

import java.util.List;

public interface ISousAnalyseService {

    SousAnalyseDTO createSousAnalyse(SousAnalyseDTO sousAnalyseDTO);

    List<SousAnalyseDTO> getAllSousAnalyses();

    SousAnalyseDTO getSousAnalyseById(Long id);

    SousAnalyseDTO updateSousAnalyse(Long sousAnalyseId, SousAnalyseDTO updatedSousAnalyseDTO);

    void deleteSousAnalyse(Long id);

    List<SousAnalyseMesuresDTO> getAllSousAnalyseMesures();

    CombinedSousAnalyseDTO getSousAnalyseMesures(Long souysAnalyseMesuresId, Long sousAnalyseId);
}

