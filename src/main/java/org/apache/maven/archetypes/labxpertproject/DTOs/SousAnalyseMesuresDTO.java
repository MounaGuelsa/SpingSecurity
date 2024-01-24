package org.apache.maven.archetypes.labxpertproject.DTOs;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SousAnalyseMesuresDTO {

    private Long sousAnalyseMesuresId;

    @NotBlank(message = "Analyse name must not be blank")
    private String analyseName;

    @NotNull(message = "Min value must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Min value must be greater than 0")
    private double min;

    @NotNull(message = "Max value must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Max value must be greater than 0")
    private double max;

    @NotBlank(message = "Unite must not be blank")
    private String unite;
}