package org.apache.maven.archetypes.labxpertproject.DTOs;

import lombok.Data;

@Data
public class CombinedSousAnalyseDTO {
    private SousAnalyseDTO sousAnalyseDTO;
    private SousAnalyseMesuresDTO sousAnalyseMesuresDTO;
}
