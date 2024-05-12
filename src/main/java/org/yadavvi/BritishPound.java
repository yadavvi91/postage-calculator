package org.yadavvi;

public class BritishPound extends Money {
    public BritishPound(Double amountInBaseCurrency) {
        this.currency =  Currency.Gbp;
        this.amount = amountInBaseCurrency;
    }
}
