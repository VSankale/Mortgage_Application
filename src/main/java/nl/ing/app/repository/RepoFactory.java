package nl.ing.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


/**
 * this can be utilized if we have different Mortage /Interest Rate
 */
@Component
public class RepoFactory {

    private final RateJpaRepository rateJpaRepository;

    public RepoFactory(RateJpaRepository rateJpaRepository) {
        this.rateJpaRepository = rateJpaRepository;
    }


    public JpaRepository<?, ?> getJpaRepository(String loanType) {

        if ("homeLoan".equalsIgnoreCase(loanType)) {
            return rateJpaRepository;
        }
        return null;
    }
}
