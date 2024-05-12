package org.yadavvi;

public class Calculator {
    public Money calculate(Integer weight, Integer height, Integer width, Integer depth, Currency currency)
            throws Exception {
        var postageInBaseCurrency = PostageInBaseCurrency(new Dimensions(weight, height, width, depth));
        return ConvertCurrency(postageInBaseCurrency, currency);
    }

    private Double PostageInBaseCurrency(Dimensions dimensions) {
        if (dimensions.weight() <= 60 && dimensions.height() <= 229 && dimensions.width() <= 162 && dimensions.depth() <= 25) {
            return 120d;
        }
        if (dimensions.weight() <= 500 && dimensions.height() <= 324 && dimensions.width() <= 229 && dimensions.depth() <= 100) {
            return (double) (dimensions.weight() * 4);
        }
        return Math.max(dimensions.weight(), dimensions.height() * dimensions.width() * dimensions.depth() / 1000d) * 6;
    }

    private Money ConvertCurrency(double amountInBaseCurrency, Currency currency) throws Exception {
        if (currency == Currency.Gbp) return new Money(Currency.Gbp, amountInBaseCurrency);
        if (currency == Currency.Eur) return new Money(Currency.Eur, (amountInBaseCurrency + 200) * 1.25d);
        if (currency == Currency.Chf) return new Money(Currency.Chf, (amountInBaseCurrency + 200) * 1.36d);
        throw new Exception("Currency not supported");
    }

    private static record Dimensions(Integer weight, Integer height, Integer width, Integer depth) {
    }
}