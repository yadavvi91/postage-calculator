package org.yadavvi;

sealed interface Package permits SmallPackage, MediumPackage, DefaultPackage {
    Double postageInBaseCurrency();
}

record SmallPackage(Dimension dimension) implements Package {
    @Override
    public Double postageInBaseCurrency() {
        return 120d;
    }
}

record MediumPackage(Dimension dimension) implements Package {
    @Override
    public Double postageInBaseCurrency() {
        return (double) (dimension.weight() * 4);
    }
}

record DefaultPackage(Dimension dimension) implements Package {
    @Override
    public Double postageInBaseCurrency() {
        return Math.max(
                dimension.weight(),
                dimension.height() * dimension.width() * dimension.depth() / 1000d) * 6;
    }
}

record Dimension(Integer weight, Integer height, Integer width, Integer depth) {
}

public class CalculatorApp {

    private static boolean isMediumPackage(Dimension dimension) {
        return dimension.weight() <= 500 && dimension.height() <= 324
                && dimension.width() <= 229 && dimension.depth() <= 100;
    }

    private static boolean isSmallPackage(Dimension dimension) {
        return dimension.weight() <= 60 && dimension.height() <= 229
                && dimension.width() <= 162 && dimension.depth() <= 25;
    }

    static Package createPackage(Dimension dimension) {
        if (isSmallPackage(dimension)) {
            return new SmallPackage(dimension);
        } else if (isMediumPackage(dimension)) {
            return new MediumPackage(dimension);
        } else {
            return new DefaultPackage(dimension);
        }
    }

    public Money calculate(Integer weight, Integer height, Integer width, Integer depth, Currency currency)
            throws Exception {
        Dimension dimension = new Dimension(weight, height, width, depth);
        Package postage = CalculatorApp.createPackage(dimension);
        var postageInBaseCurrency = postage.postageInBaseCurrency();
        return currency.convertCurrency(postageInBaseCurrency);
    }
}
