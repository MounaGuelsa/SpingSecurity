package org.apache.maven.archetypes.labxpertproject.service.interfaces;

import org.apache.maven.archetypes.labxpertproject.DTOs.ReactifDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IReactifService {

    ReactifDTO addReactif(ReactifDTO reactifDTO);

    List<ReactifDTO> getAllReactifs();

    ReactifDTO getReactifById(Long id);

    @Transactional
    ReactifDTO updateReactif(ReactifDTO reactifDTO);

    @Transactional
    void deleteReactif(Long id);
}
