package org.yadavvi;

public abstract class Money {

    public Currency currency;
    public Double amountInBaseCurrency;

    public Money(Currency currency, Double amountInBaseCurrency) {
        this.currency = currency;
        this.amountInBaseCurrency = amountInBaseCurrency;
    }

    static Money of(Currency currency, Double amountInBaseCurrency) {
        return switch (currency) {
            case Gbp -> new BritishPound(amountInBaseCurrency);
            case Eur -> new Euro(amountInBaseCurrency);
            case Chf -> new SwissFranc(amountInBaseCurrency);
        };
    }

    @Override
    public String toString() {
        return STR."Currency: \{currency}, Amount: \{amountInBaseCurrency}";
    }

}