package org.apache.maven.archetypes.labxpertproject.service.implimentation;
import org.apache.maven.archetypes.labxpertproject.DTOs.SousAnalyseDTO;
import org.apache.maven.archetypes.labxpertproject.DTOs.SousAnalyseMesuresDTO;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.AnalyseType;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.StatutDanalyse;
import org.apache.maven.archetypes.labxpertproject.entitiy.enums.StatutDeResultat;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.*;
import org.apache.maven.archetypes.labxpertproject.repository.*;
import org.apache.maven.archetypes.labxpertproject.service.interfaces.IAnalyseService;


import org.apache.maven.archetypes.labxpertproject.DTOs.AnalyseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalyseServiceImpl implements IAnalyseService {

    @Autowired
    private AnalyseRepository analyseRepository;

    @Autowired
    private ReactifRepository reactifRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EchantillonRepository echantillonRepository;

    @Autowired
    private PlanificationRepository planificationRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private SousAnalyseRepository sousAnalyseRepository;

    @Autowired
    private SousAnalyseMesuresRepository sousAnalyseMesuresRepository;

    public AnalyseDTO getAnalyseById(Long id) {
        Analyse analyse = analyseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Analyse not found with id: " + id));

        AnalyseDTO analyseDTO = modelMapper.map(analyse, AnalyseDTO.class);

        // Map sousAnalyses
        List<SousAnalyseDTO> sousAnalyseDTOs = analyse.getSousAnalyses().stream()
                .map(sousAnalyse -> {
                    SousAnalyseDTO sousAnalyseDTO = modelMapper.map(sousAnalyse, SousAnalyseDTO.class);
                    sousAnalyseDTO.setSousAnalyseMesures(modelMapper.map(sousAnalyse.getSousAnalyseMesures(), SousAnalyseMesuresDTO.class));
                    return sousAnalyseDTO;
                })
                .collect(Collectors.toList());

        analyseDTO.setSousAnalyses(sousAnalyseDTOs);

        return analyseDTO;
    }

    public List<AnalyseDTO> getAllAnalyses() {
        List<Analyse> analyses = analyseRepository.findAll();
        return analyses.stream()
                .map(analyse -> modelMapper.map(analyse, AnalyseDTO.class))
                .collect(Collectors.toList());
    }

    public AnalyseDTO addAnalyse(AnalyseDTO analyseDTO) {
        long count = analyseRepository.countByEtatAnalyse(StatutDanalyse.EN_COURS_DANALYSE);
        if (count >= 5) {
            throw new RuntimeException("The lab is full");
        } else {
            analyseDTO.setEtatAnalyse(StatutDanalyse.EN_COURS_DANALYSE);

            // Check reactif quantities
            for (Long reactifId : analyseDTO.getReactifsIds()) {
                Reactif reactif = reactifRepository.findById(reactifId)
                        .orElseThrow(() -> new EntityNotFoundException("Reactif not found with id: " + reactifId));
                if (reactif.getQuantite() <= 0) {
                    throw new RuntimeException("Insufficient quantity for reactif with id: " + reactifId);
                }
            }

            Echantillon echantillon = echantillonRepository.findById(analyseDTO.getEchantillonId())
                    .orElseThrow(() -> new EntityNotFoundException("Echantillon not found with id: " + analyseDTO.getEchantillonId()));
            Planification planification = planificationRepository.findById(analyseDTO.getPlanificationId())
                    .orElseThrow(() -> new EntityNotFoundException("Planification not found with id: " + analyseDTO.getPlanificationId()));
            Utilisateur utilisateur = utilisateurRepository.findById(analyseDTO.getUtilisateurId())
                    .orElseThrow(() -> new EntityNotFoundException("Utilisateur not found with id: " + analyseDTO.getUtilisateurId()));

            Analyse analyse = modelMapper.map(analyseDTO, Analyse.class);

            analyse.setEchantillon(echantillon);
            analyse.setPlanification(planification);
            analyse.setUtilisateur(utilisateur);

            // Save the Analyse entity first
            analyse = analyseRepository.save(analyse);

            List<SousAnalyse> sousAnalyses = new ArrayList<>();
            for (SousAnalyseDTO sousAnalyseDTO : analyseDTO.getSousAnalyses()) {
                SousAnalyseMesures sousAnalyseMesures = sousAnalyseMesuresRepository.findById(sousAnalyseDTO.getSousAnalyseMesuresId())
                        .orElseThrow(() -> new EntityNotFoundException("SousAnalyseMesures not found with id: " + sousAnalyseDTO.getSousAnalyseMesuresId()));

                SousAnalyse sousAnalyse = new SousAnalyse();
                sousAnalyse.setValeur(sousAnalyseDTO.getValeur());
                sousAnalyse.setStatutDeResultat(compareValeurWithMinMax(sousAnalyseDTO.getValeur(), sousAnalyseMesures.getMin(), sousAnalyseMesures.getMax()));
                sousAnalyse.setSousAnalyseMesures(sousAnalyseMesures);
                sousAnalyse.setAnalyse(analyse); // set the saved Analyse in the SousAnalyse
                sousAnalyse = sousAnalyseRepository.save(sousAnalyse);

                sousAnalyses.add(sousAnalyse);
            }

            analyse.setSousAnalyses(sousAnalyses);
            analyse.setDateDebutAnalyse(LocalDate.now());
            analyse.setAnalyseType(AnalyseType.CHIMIE);

            Analyse savedAnalyse = analyseRepository.save(analyse);

            AnalyseDTO resultDTO = modelMapper.map(savedAnalyse, AnalyseDTO.class);
            resultDTO.getSousAnalyses().forEach(sousAnalyseDTO -> {
                sousAnalyseDTO.setSousAnalyseMesures(modelMapper.map(sousAnalyseMesuresRepository.findById(sousAnalyseDTO.getSousAnalyseMesuresId()).get(), SousAnalyseMesuresDTO.class));
            });

            return resultDTO;
        }
    }
    public AnalyseDTO updateAnalyse(Long id, AnalyseDTO analyseDTO) {
        Analyse existingAnalyse = analyseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Analyse not found with id: " + id));

        Analyse newAnalyse = modelMapper.map(analyseDTO, Analyse.class);

        if (newAnalyse.getUtilisateur() != null) {
            existingAnalyse.setUtilisateur(newAnalyse.getUtilisateur());
        }
        if (newAnalyse.getDateDebutAnalyse() != null) {
            existingAnalyse.setDateDebutAnalyse(newAnalyse.getDateDebutAnalyse());
        }
        if (newAnalyse.getDateFinAnalyse() != null) {
            existingAnalyse.setDateFinAnalyse(newAnalyse.getDateFinAnalyse());
        }
        if (newAnalyse.getEtatAnalyse() != null) {
            existingAnalyse.setEtatAnalyse(newAnalyse.getEtatAnalyse());
        }
        if (newAnalyse.getCommentaire() != null) {
            existingAnalyse.setCommentaire(newAnalyse.getCommentaire());
        }
        if (newAnalyse.getAnalyseType() != null) {
            existingAnalyse.setAnalyseType(newAnalyse.getAnalyseType());
        }
        if (newAnalyse.getPlanification() != null) {
            existingAnalyse.setPlanification(newAnalyse.getPlanification());
        }
        if (newAnalyse.getEchantillon() != null) {
            existingAnalyse.setEchantillon(newAnalyse.getEchantillon());
        }
        if (newAnalyse.getReactifs() != null) {
            existingAnalyse.setReactifs(newAnalyse.getReactifs());
        }

        if (analyseDTO.getSousAnalyses() != null) {
            for (SousAnalyseDTO sousAnalyseDTO : analyseDTO.getSousAnalyses()) {
                SousAnalyse existingSousAnalyse = sousAnalyseRepository.findById(sousAnalyseDTO.getSousAnalyseId())
                        .orElseThrow(() -> new EntityNotFoundException("SousAnalyse not found with id: " + sousAnalyseDTO.getSousAnalyseId()));

                SousAnalyseMesures sousAnalyseMesures = sousAnalyseMesuresRepository.findById(sousAnalyseDTO.getSousAnalyseMesuresId())
                        .orElseThrow(() -> new EntityNotFoundException("SousAnalyseMesures not found with id: " + sousAnalyseDTO.getSousAnalyseMesuresId()));

                    existingSousAnalyse.setValeur(sousAnalyseDTO.getValeur());

                if (sousAnalyseDTO.getSousAnalyseMesuresId() != null) {
                    existingSousAnalyse.setSousAnalyseMesures(sousAnalyseMesures);
                }
                    existingSousAnalyse.setStatutDeResultat(compareValeurWithMinMax(sousAnalyseDTO.getValeur(), sousAnalyseMesures.getMin(), sousAnalyseMesures.getMax()));


                sousAnalyseRepository.save(existingSousAnalyse);
            }
        }

        Analyse updatedAnalyse = analyseRepository.save(existingAnalyse);

        return modelMapper.map(updatedAnalyse, AnalyseDTO.class);
    }

    public void deleteAnalyse(Long id) {
        Analyse existingAnalyse = analyseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Analyse not found with id: " + id));
        analyseRepository.delete(existingAnalyse);
    }

    private StatutDeResultat compareValeurWithMinMax(double valeur, double min, double max) {
        if (valeur >= min && valeur <= max) {
            return StatutDeResultat.NORMAL;
        } else {
            return StatutDeResultat.ANORMAL;
        }
    }
}
