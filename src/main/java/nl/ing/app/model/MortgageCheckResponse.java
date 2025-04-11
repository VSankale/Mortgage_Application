package nl.ing.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MortgageCheckResponse {

    @JsonProperty
    @JsonInclude(JsonInclude.Include.ALWAYS)
    boolean feasible;


    @JsonProperty
    BigDecimal monthlyCost=BigDecimal.ZERO;

    public MortgageCheckResponse (boolean feasible){
        this.feasible = feasible;
    }
}
