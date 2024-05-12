package org.yadavvi;

import java.util.Objects;

public class Calculator {
    public Money calculate(Integer weight, Integer height, Integer width, Integer depth, Currency currency)
            throws Exception {
        ParcelPackage parcelPackage = ParcelPackage.of(weight, height, width, depth);
        var postageInBaseCurrency = parcelPackage.postageInBaseCurrency();
        return ConvertCurrency(postageInBaseCurrency, currency);
    }

    private Money ConvertCurrency(double amountInBaseCurrency, Currency currency) throws Exception {
        if (currency == Currency.Gbp) return new Money(Currency.Gbp, amountInBaseCurrency);
        if (currency == Currency.Eur) return new Money(Currency.Eur, (amountInBaseCurrency + 200) * 1.25d);
        if (currency == Currency.Chf) return new Money(Currency.Chf, (amountInBaseCurrency + 200) * 1.36d);
        throw new Exception("Currency not supported");
    }


}