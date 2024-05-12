package org.yadavvi;

public class SwissFranc extends Money {
    public SwissFranc(Double amountInBaseCurrency) {
        this.currency =  Currency.Chf;
        this.amount = (amountInBaseCurrency + 200) * 1.36d;
    }
}
