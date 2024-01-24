package org.apache.maven.archetypes.labxpertproject.service.implimentation;

import org.apache.maven.archetypes.labxpertproject.DTOs.ReactifDTO;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.Reactif;
import org.apache.maven.archetypes.labxpertproject.repository.AnalyseRepository;
import org.apache.maven.archetypes.labxpertproject.repository.ReactifRepository;
import org.apache.maven.archetypes.labxpertproject.service.interfaces.IReactifService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReactifServiceImpl implements IReactifService {

    @Autowired
    private ReactifRepository reactifRepository;

    @Autowired
    private AnalyseRepository analyseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ReactifDTO addReactif(ReactifDTO reactifDTO) {
        try {
            Reactif existingReactif = reactifRepository.findByNomIgnoreCase(reactifDTO.getNom());

            if (existingReactif != null) {

                existingReactif.setQuantite(existingReactif.getQuantite() + reactifDTO.getQuantite());
                reactifRepository.save(existingReactif);

                System.out.println("Reactif quantity updated successfully in the service");
                return convertToDTO(existingReactif);
            } else {

                Reactif reactif = convertToEntity(reactifDTO);
                reactif = reactifRepository.save(reactif);
                System.out.println("Reactif added successfully in the service");
                return convertToDTO(reactif);
            }
        } catch (IllegalArgumentException e) {

            System.out.println("IllegalArgumentException: " + e.getMessage());
            throw e;
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<ReactifDTO> getAllReactifs() {
        try {
            List<Reactif> reactifs = reactifRepository.findAll();
            return reactifs.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error getting all reactifs", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ReactifDTO getReactifById(Long id) {
        try {
            Reactif reactif = reactifRepository.findById(id).orElse(null);
            return convertToDTO(reactif);
        } catch (Exception e) {
            throw new RuntimeException("Error getting reactif by id", e);
        }
    }

    @Override
    @Transactional
    public ReactifDTO updateReactif(ReactifDTO reactifDTO) {
        try {
            Reactif existingReactif = reactifRepository.findById(reactifDTO.getReactifId()).orElse(null);
            if (existingReactif != null) {
                modelMapper.map(reactifDTO, existingReactif);
                reactifRepository.save(existingReactif);
                return convertToDTO(existingReactif);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error updating  reactif", e);
        }
    }

    @Override
    @Transactional
    public void deleteReactif(Long id) {
        try {
            reactifRepository.deleteById(id);
            System.out.println("Reactif deleted successfully service");
        } catch (Exception e) {
            throw new RuntimeException("Error deleting reactif", e);
        }
    }

    private ReactifDTO convertToDTO(Reactif reactif) {
        return modelMapper.map(reactif, ReactifDTO.class);
    }

    private Reactif convertToEntity(ReactifDTO reactifDTO) {
        return modelMapper.map(reactifDTO, Reactif.class);
    }
}
