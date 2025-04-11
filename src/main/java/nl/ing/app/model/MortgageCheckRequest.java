package nl.ing.app.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.ing.app.enums.MaturityPeriod;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MortgageCheckRequest {

    //income (Amount), maturityPeriod (integer), loanValue (Amount), homeValue//(Amount).

    @NotNull
    @JsonProperty
    @JsonInclude(JsonInclude.Include.ALWAYS)
    @PositiveOrZero
    @Valid
    private BigDecimal income;

    @NotNull
    @JsonProperty
    @JsonInclude(JsonInclude.Include.ALWAYS)
    @Valid
    private int maturityPeriod;

    @NotNull
    @JsonProperty
    @JsonInclude(JsonInclude.Include.ALWAYS)
    @Positive
    @Valid
    private BigDecimal loanValue;

    @NotNull
    @JsonProperty
    @JsonInclude(JsonInclude.Include.ALWAYS)
    @Positive
    @Valid
    private BigDecimal homeValue;
}
