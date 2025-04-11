package nl.ing.app.repository;

import nl.ing.app.entity.InterestRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateJpaRepository extends JpaRepository<InterestRate, Long> {


}
