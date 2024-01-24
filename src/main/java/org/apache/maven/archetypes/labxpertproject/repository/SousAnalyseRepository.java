package org.apache.maven.archetypes.labxpertproject.repository;

import org.apache.maven.archetypes.labxpertproject.entitiy.model.SousAnalyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SousAnalyseRepository extends JpaRepository<SousAnalyse, Long> {

}
