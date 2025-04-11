package nl.ing.app.enums;

import lombok.Getter;

@Getter
public enum MaturityPeriod {

   FIVE_YEARS(5), TEN_YEARS(10), FIFTEEN_YEARS(15),
    TWENTY_YEARS(20), THIRTY_YEARS(30);

    final int maturityPeriodInYears;


    MaturityPeriod (int maturityPeriodInYears) {
       this.maturityPeriodInYears = maturityPeriodInYears;
    }

}
