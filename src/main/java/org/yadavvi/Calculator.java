package org.yadavvi;

public class Calculator {
    public Money calculate(Integer weight, Integer height, Integer width, Integer depth, Currency currency)
            throws Exception {
        var postageInBaseCurrency = new Dimensions(weight, height, width, depth).PostageInBaseCurrency();
        return ConvertCurrency(postageInBaseCurrency, currency);
    }

    private Money ConvertCurrency(double amountInBaseCurrency, Currency currency) throws Exception {
        if (currency == Currency.Gbp) return new Money(Currency.Gbp, amountInBaseCurrency);
        if (currency == Currency.Eur) return new Money(Currency.Eur, (amountInBaseCurrency + 200) * 1.25d);
        if (currency == Currency.Chf) return new Money(Currency.Chf, (amountInBaseCurrency + 200) * 1.36d);
        throw new Exception("Currency not supported");
    }

    private record Dimensions(Integer weight, Integer height, Integer width, Integer depth) {
        private Double PostageInBaseCurrency() {
            if (weight() <= 60 && height() <= 229 && width() <= 162 && depth() <= 25) {
                return 120d;
            }
            if (weight() <= 500 && height() <= 324 && width() <= 229 && depth() <= 100) {
                return (double) (weight() * 4);
            }
            return Math.max(weight(), height() * width() * depth() / 1000d) * 6;
        }
    }
}