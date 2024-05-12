package org.yadavvi;

public class BritishPound extends Money {

    public Double amount;

    public BritishPound(Double amountInBaseCurrency) {
        super(Currency.Gbp, amountInBaseCurrency);
        this.amount = amountInBaseCurrency;
    }
}
