package nl.ing.app.component;

import lombok.extern.slf4j.Slf4j;
import nl.ing.app.entity.InterestRate;
import nl.ing.app.model.MortgageCheckRequest;
import nl.ing.app.model.MortgageCheckResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_EVEN;

@Component
@Slf4j
public class MortgageChecker {

    public MortgageCheckResponse checkLoanFeasibility(MortgageCheckRequest mortgageCheckRequest, InterestRate interestRates) {

        boolean loanFeasible = isLoanAmountFeasible(mortgageCheckRequest);
        log.info("loanFeasible: {}", loanFeasible);
        if (loanFeasible) {
            MortgageCheckResponse mortgageCheckResponse = new MortgageCheckResponse();
            mortgageCheckResponse.setFeasible(true);
            mortgageCheckResponse.setMonthlyCost(calculateEmi(mortgageCheckRequest.getLoanValue(), interestRates));
            log.info("mortgageCheckResponse: {}", mortgageCheckResponse);
            return mortgageCheckResponse;
        }

        return new MortgageCheckResponse(loanFeasible);
    }

    private boolean isLoanAmountFeasible(MortgageCheckRequest mortgageCheckRequest) {
        BigDecimal fourTimesIncomeAmount = mortgageCheckRequest.getIncome().multiply(BigDecimal.valueOf(4));
        if (mortgageCheckRequest.getLoanValue().compareTo(fourTimesIncomeAmount) > 0) {
            return false;
        } else if (mortgageCheckRequest.getLoanValue().compareTo(mortgageCheckRequest.getHomeValue()) > 0) {
            return false;
        }else {
            return true;
        }
    }


    private BigDecimal calculateEmi(BigDecimal loanValue, InterestRate interestRate) {

        double powIntRate = interestRate.getRate().doubleValue();

        return (loanValue
                .multiply(interestRate.getRate())
                .multiply(BigDecimal.valueOf(Math.pow(1 + powIntRate, interestRate.getMaturityPeriod()))))
                .divide(
                        BigDecimal.valueOf(Math.pow(1 + powIntRate, interestRate.getMaturityPeriod()) - 1), HALF_EVEN);


    }
}
