package org.yadavvi;

public class Calculator {
    public Money calculate(Integer weight, Integer height, Integer width, Integer depth, Currency currency)
            throws Exception {
        ParcelPackage parcelPackage = ParcelPackage.of(weight, height, width, depth);
        var postageInBaseCurrency = parcelPackage.postageInBaseCurrency();
        Money money = convertCurrency(currency, postageInBaseCurrency);
        return money;
    }

    private Money convertCurrency(Currency currency, double amountInBaseCurrency) throws Exception {
        return Money.of(currency, amountInBaseCurrency);
    }

}