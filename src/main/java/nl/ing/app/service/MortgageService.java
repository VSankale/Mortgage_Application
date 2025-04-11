package nl.ing.app.service;

import nl.ing.app.entity.InterestRate;
import nl.ing.app.model.MortgageCheckRequest;
import nl.ing.app.model.MortgageCheckResponse;

import java.util.List;

public interface MortgageService {

    /**
     *
     * @return fetch list of interrates available for a Mortgage type
     * @throws Exception
     */
    List<InterestRate> fetchInterestRate() throws Exception;

    /**
     * check Mortgage feasibility on the basis of input request param
     * @param mortgageCheckRequest
     * @return
     */
    MortgageCheckResponse checkLoanFeasibility(MortgageCheckRequest mortgageCheckRequest) throws Exception;;

}
