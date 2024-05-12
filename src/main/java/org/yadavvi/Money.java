package org.yadavvi;

public abstract class Money {

    public Currency currency;
    public Double amount;

    static Money of(Currency currency, Double amountInBaseCurrency) {
        return switch (currency) {
            case Gbp -> new BritishPound(amountInBaseCurrency);
            case Eur -> new Euro(amountInBaseCurrency);
            case Chf -> new SwissFranc(amountInBaseCurrency);
        };
    }

    @Override
    public String toString() {
        return STR."Currency: \{currency}, Amount: \{amount}";
    }

    public static class BritishPound extends Money {
        public BritishPound(Double amountInBaseCurrency) {
            this.currency = Currency.Gbp;
            this.amount = amountInBaseCurrency;
        }
    }

    public static class Euro extends Money {
        public Euro(Double amountInBaseCurrency) {
            this.currency = Currency.Eur;
            this.amount = (amountInBaseCurrency + 200) * 1.25d;
        }
    }

    public static class SwissFranc extends Money {
        public SwissFranc(Double amountInBaseCurrency) {
            this.currency = Currency.Chf;
            this.amount = (amountInBaseCurrency + 200) * 1.36d;
        }
    }
}