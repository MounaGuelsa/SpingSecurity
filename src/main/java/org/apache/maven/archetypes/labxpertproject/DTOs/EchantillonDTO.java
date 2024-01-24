package org.apache.maven.archetypes.labxpertproject.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.maven.archetypes.labxpertproject.entitiy.model.Patient;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@Data
public class EchantillonDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Positive(message = "echantillonId must be a positive number")
    private Long echantillonId;

    @NotNull(message = "patientId must not be null")
    @Positive(message = "patientId must be a positive number")
    private Long patientId;

    private PatientDTO patient; // Include PatientDTO field

    @NotNull(message = "datePrelevement must not be null")
    @PastOrPresent(message = "The datePrelevement must be in the past or present")
    private LocalDate datePrelevement;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AnalyseDTO> analyses;
}
