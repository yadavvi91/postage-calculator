package org.yadavvi;

public class SwissFranc extends Money {
    public Double amount;

    public SwissFranc(Double amountInBaseCurrency) {
        super(Currency.Chf, amountInBaseCurrency);
        this.amount = (amountInBaseCurrency + 200) * 1.36d;
    }
}
