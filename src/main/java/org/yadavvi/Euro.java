package org.yadavvi;

public class Euro extends Money {
    public Euro(Double amountInBaseCurrency) {
        this.currency =  Currency.Eur;
        this.amount = (amountInBaseCurrency + 200) * 1.25d;
    }
}
