package org.yadavvi;

public class Euro extends Money {
    public Double amount;

    public Euro(Double amountInBaseCurrency) {
        super(Currency.Eur, amountInBaseCurrency);
        this.amount = (amountInBaseCurrency + 200) * 1.25d;
    }
}
