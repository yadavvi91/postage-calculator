package org.yadavvi;

public enum Currency {
    Gbp,
    Eur,
    Chf;

    Money convertCurrency(double amountInBaseCurrency) throws Exception {
        return Money.of(this, amountInBaseCurrency);
    }
}