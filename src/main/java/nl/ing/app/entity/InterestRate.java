package nl.ing.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "homeloan_interest_rate")
public class InterestRate {

    //The mortgage rate object contains the fields; maturityPeriod (integer),
    //interestRate (Percentage) and lastUpdate (Timestamp)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "maturity_period")
    private int maturityPeriod;

    private BigDecimal rate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}
