package org.yadavvi;

import static java.lang.StringTemplate.STR;

public class Money {

    public Currency currency;
    public Double amount;
    public Money(Currency currency, Double amount) {
        this.currency = currency;
        this.amount = amount;
    }


    @Override
    public String toString() {
        return STR."Currency: \{currency}, Amount: \{amount}";
    }
}