package com.currency.currencyexchangeapi.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyResponse {
    private String currency;
    private String month1;
    private String month2;
    private String month3;
    private String month4;
    private String month5;
    private String month6;
    private String month7;
    private String month8;
    private String month9;
    private String month10;
    private String month11;
    private String month12;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyResponse)) return false;
        CurrencyResponse that = (CurrencyResponse) o;
        return Objects.equals(getCurrency(), that.getCurrency()) &&
                Objects.equals(getMonth1(), that.getMonth1()) &&
                Objects.equals(getMonth2(), that.getMonth2()) &&
                Objects.equals(getMonth3(), that.getMonth3()) &&
                Objects.equals(getMonth4(), that.getMonth4()) &&
                Objects.equals(getMonth5(), that.getMonth5()) &&
                Objects.equals(getMonth6(), that.getMonth6()) &&
                Objects.equals(getMonth7(), that.getMonth7()) &&
                Objects.equals(getMonth8(), that.getMonth8()) &&
                Objects.equals(getMonth9(), that.getMonth9()) &&
                Objects.equals(getMonth10(), that.getMonth10()) &&
                Objects.equals(getMonth11(), that.getMonth11()) &&
                Objects.equals(getMonth12(), that.getMonth12());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrency(), getMonth1(), getMonth2(), getMonth3(), getMonth4(), getMonth5(), getMonth6(), getMonth7(), getMonth8(), getMonth9(), getMonth10(), getMonth11(), getMonth12());
    }
}
