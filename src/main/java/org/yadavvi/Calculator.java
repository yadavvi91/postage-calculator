package org.yadavvi;

public class Calculator {
    public Money calculate(Integer weight, Integer height, Integer width, Integer depth, Currency currency)
            throws Exception {
        ParcelPackage parcelPackage = ParcelPackage.of(weight, height, width, depth);
        var postageInBaseCurrency = parcelPackage.postageInBaseCurrency();
        return ConvertCurrency(postageInBaseCurrency, currency);
    }

    private Money ConvertCurrency(double amountInBaseCurrency, Currency currency) throws Exception {
        return Money.of(currency, amountInBaseCurrency);
    }

}