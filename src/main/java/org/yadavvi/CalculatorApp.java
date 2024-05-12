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

    private static boolean isMediumPackage(Integer weight, Integer height, Integer width, Integer depth) {
        return weight <= 500 && height <= 324 && width <= 229 && depth <= 100;
    }

    private static boolean isSmallPackage(Integer weight, Integer height, Integer width, Integer depth) {
        return weight <= 60 && height <= 229 && width <= 162 && depth <= 25;
    }

    static Package createPackage(Integer weight, Integer height, Integer width, Integer depth) {
        Dimension dimension = new Dimension(weight, height, width, depth);
        if (isSmallPackage(weight, height, width, depth)) {
            return new SmallPackage(dimension);
        } else if (isMediumPackage(weight, height, width, depth)) {
            return new MediumPackage(dimension);
        } else {
            return new DefaultPackage(dimension);
        }
    }

    public Money calculate(Integer weight, Integer height, Integer width, Integer depth, Currency currency)
            throws Exception {
        Package postage = CalculatorApp.createPackage(weight, height, width, depth);
        var postageInBaseCurrency = postage.postageInBaseCurrency();
        return currency.convertCurrency(postageInBaseCurrency);
    }
}
