package nl.ing.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import nl.ing.app.component.MortgageChecker;
import nl.ing.app.entity.InterestRate;
import nl.ing.app.exception.CustomException;
import nl.ing.app.model.MortgageCheckRequest;
import nl.ing.app.model.MortgageCheckResponse;
import nl.ing.app.repository.RateJpaRepository;
import nl.ing.app.service.MortgageService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class MortgageServiceImpl implements MortgageService {

    private final RateJpaRepository rateJpaRepository;
    private final MortgageChecker mortgageChecker;

    public MortgageServiceImpl(RateJpaRepository rateJpaRepository1, MortgageChecker mortgageChecker, MortgageChecker mortgageChecker1) {
        this.rateJpaRepository = rateJpaRepository1;

        this.mortgageChecker = mortgageChecker1;
    }

    @Override
    public List<InterestRate> fetchInterestRate() {
        List<InterestRate> list = null;
        try {
            list = rateJpaRepository.findAll();
            if (!CollectionUtils.isEmpty(list)) {
                return list;
            }

        } catch (Exception e) {
            log.error("error while getting interest rates:  {}", e.getMessage());
            throw e;
        }
        return list;
    }

    @Override
    public MortgageCheckResponse checkLoanFeasibility(MortgageCheckRequest mortgageCheckRequest) throws Exception {
        List<InterestRate> interestRates = fetchInterestRate();
        log.info("mortgageCheckRequest: {}", mortgageCheckRequest);
        AtomicReference<MortgageCheckResponse> mortgageCheckResponse = new AtomicReference<>();
        interestRates.forEach(x -> {
            if (x.getMaturityPeriod() == mortgageCheckRequest.getMaturityPeriod()) {
                log.info("interestRate: {}", x);
                MortgageCheckResponse mortgageCheckResponse1 = mortgageChecker.checkLoanFeasibility(mortgageCheckRequest, x);
                mortgageCheckResponse.set(mortgageCheckResponse1);
            }
        });

        if (mortgageCheckResponse.get() == null) {
            throw new CustomException("Interest Rate not available for MaturityPeriod " + mortgageCheckRequest
                    .getMaturityPeriod() );
        }
        return mortgageCheckResponse.get();
    }

}
