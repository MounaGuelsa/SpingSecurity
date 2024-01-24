package org.apache.maven.archetypes.labxpertproject.service.interfaces;

import org.apache.maven.archetypes.labxpertproject.DTOs.AnalyseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAnalyseService {
   AnalyseDTO addAnalyse(AnalyseDTO analyseDTO);

    List<AnalyseDTO> getAllAnalyses();

    AnalyseDTO getAnalyseById(Long id);

    AnalyseDTO updateAnalyse(Long id, AnalyseDTO analyseDTO);

    void deleteAnalyse(Long id);
}
