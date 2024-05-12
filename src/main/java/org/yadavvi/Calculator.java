package org.yadavvi;

public class Calculator {
    public Money calculate(Integer weight, Integer height, Integer width, Integer depth, Currency currency)
            throws Exception {
        Package aPackage = Package.of(weight, height, width, depth);
        var postageInBaseCurrency = aPackage.postageInBaseCurrency();
        return ConvertCurrency(postageInBaseCurrency, currency);
    }

    private Money ConvertCurrency(double amountInBaseCurrency, Currency currency) throws Exception {
        if (currency == Currency.Gbp) return new Money(Currency.Gbp, amountInBaseCurrency);
        if (currency == Currency.Eur) return new Money(Currency.Eur, (amountInBaseCurrency + 200) * 1.25d);
        if (currency == Currency.Chf) return new Money(Currency.Chf, (amountInBaseCurrency + 200) * 1.36d);
        throw new Exception("Currency not supported");
    }

    // So we got 3 types of different packages
    // Let's move them to their respective classes
    private record Package(Integer weight, Integer height, Integer width, Integer depth) {
        public static Package of(Integer weight, Integer height, Integer width, Integer depth) {
            return new Package(weight, height, width, depth);
        }

        private Double postageInBaseCurrency() {
            if (isSmallPackage()) {
                return 120d;
            }
            if (isMediumPackage()) {
                return (double) (weight() * 4);
            }
            // else isLargePackage() (isDefaultPackage()?)
            return Math.max(weight(), height() * width() * depth() / 1000d) * 6;
        }

        private boolean isMediumPackage() {
            return weight() <= 500 && height() <= 324 && width() <= 229 && depth() <= 100;
        }

        private boolean isSmallPackage() {
            return weight() <= 60 && height() <= 229 && width() <= 162 && depth() <= 25;
        }
    }
}