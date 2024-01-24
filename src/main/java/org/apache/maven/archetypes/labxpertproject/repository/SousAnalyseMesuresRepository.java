package org.apache.maven.archetypes.labxpertproject.repository;

import org.apache.maven.archetypes.labxpertproject.entitiy.model.SousAnalyseMesures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SousAnalyseMesuresRepository extends JpaRepository<SousAnalyseMesures, Long> {
}
