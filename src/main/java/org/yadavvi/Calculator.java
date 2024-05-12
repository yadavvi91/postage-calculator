package org.yadavvi;

import java.util.Objects;

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

    interface PostageInCurrency {
        Double postageInBaseCurrency();
    }

    // So we got 3 types of different packages
    // Let's move them to their respective classes
    abstract static class Package implements PostageInCurrency {
        final Integer weight;
        final Integer height;
        final Integer width;
        final Integer depth;

        private Package(Integer weight, Integer height, Integer width, Integer depth) {
            this.weight = weight;
            this.height = height;
            this.width = width;
            this.depth = depth;
        }

        public static Package of(Integer weight, Integer height, Integer width, Integer depth) {
            if (weight <= 60 && height <= 229 && width <= 162 && depth <= 25) {
                return new SmallPackage(weight, height, width, depth);
            } else if (weight <= 500 && height <= 324 && width <= 229 && depth <= 100) {
                return new MediumPackage(weight, height, width, depth);
            } else {
                return new DefaultPackage(weight, height, width, depth);
            }
        }

        public abstract Double postageInBaseCurrency();

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Package) obj;
            return Objects.equals(this.weight, that.weight) &&
                    Objects.equals(this.height, that.height) &&
                    Objects.equals(this.width, that.width) &&
                    Objects.equals(this.depth, that.depth);
        }

        @Override
        public int hashCode() {
            return Objects.hash(weight, height, width, depth);
        }

        @Override
        public String toString() {
            return STR."Package[weight=\{weight}, height=\{height}, width=\{width}, depth=\{depth}\{']'}";
        }
    }

    private static final class SmallPackage extends Package {
        private SmallPackage(Integer weight, Integer height, Integer width, Integer depth) {
            super(weight, height, width, depth);
        }

        public Double postageInBaseCurrency() {
            return 120d;
        }

    }

    private static final class MediumPackage extends Package {
        private MediumPackage(Integer weight, Integer height, Integer width, Integer depth) {
            super(weight, height, width, depth);
        }

        public Double postageInBaseCurrency() {
            return (double) (weight * 4);
        }

    }

    private static final class DefaultPackage extends Package {
        private DefaultPackage(Integer weight, Integer height, Integer width, Integer depth) {
            super(weight, height, width, depth);
        }

        public Double postageInBaseCurrency() {
            return Math.max(weight, height * width * depth / 1000d) * 6;
        }

    }

}