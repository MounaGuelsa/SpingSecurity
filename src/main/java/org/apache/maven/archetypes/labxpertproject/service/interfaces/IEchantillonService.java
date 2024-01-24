package org.apache.maven.archetypes.labxpertproject.service.interfaces;

import org.apache.maven.archetypes.labxpertproject.DTOs.EchantillonDTO;

import java.util.List;

public interface IEchantillonService {

    EchantillonDTO getEchantillonById(Long id);

    List<EchantillonDTO> getAllEchantillons();

    EchantillonDTO createEchantillon(EchantillonDTO echantillonDTO);

    public EchantillonDTO updateEchantillon(Long echantillonId, EchantillonDTO updatedEchantillonDTO);

    void deleteEchantillon(Long id);
}
