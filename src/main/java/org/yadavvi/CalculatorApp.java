package org.yadavvi;

sealed interface PostageValue permits PostageValue.DefaultPackage, PostageValue.MediumPackage,
        PostageValue.SmallPackage {
    Double postageInBaseCurrency();

    record SmallPackage(Dimension dimension) implements PostageValue {
        @Override
        public Double postageInBaseCurrency() {
            return 120d;
        }
    }

    record MediumPackage(Dimension dimension) implements PostageValue {
        @Override
        public Double postageInBaseCurrency() {
            return (double) (dimension.weight() * 4);
        }
    }

    record DefaultPackage(Dimension dimension) implements PostageValue {
        @Override
        public Double postageInBaseCurrency() {
            return Math.max(
                    dimension.weight(),
                    dimension.height() * dimension.width() * dimension.depth() / 1000d) * 6;
        }
    }
}

record Dimension(Integer weight, Integer height, Integer width, Integer depth) {
}

public class CalculatorApp {

    public static PostageValue of(Integer weight, Integer height, Integer width, Integer depth) {
        Dimension dimension = new Dimension(weight, height, width, depth);
        String type = null;
        if (weight <= 60 && height <= 229 && width <= 162 && depth <= 25) {
            type = "SMALL";
        } else if (weight <= 500 && height <= 324 && width <= 229 && depth <= 100) {
            type = "MEDIUM";
        } else {
            type = "DEFAULT";
        }
        return switch (type) {
            case "SMALL" -> new PostageValue.SmallPackage(dimension);
            case "MEDIUM" -> new PostageValue.MediumPackage(dimension);
            case "DEFAULT" -> new PostageValue.DefaultPackage(dimension);
            default -> throw new IllegalStateException(STR."Unexpected value: \{type}");
        };

    }

    public Money calculate(Integer weight, Integer height, Integer width, Integer depth, Currency currency)
            throws Exception {
        PostageValue postage = CalculatorApp.of(weight, height, width, depth);
        var postageInBaseCurrency = postage.postageInBaseCurrency();
        return currency.convertCurrency(postageInBaseCurrency);
    }
}
