package org.yadavvi;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class ParcelPackage implements PostageInCurrency {
    final Integer weight;
    final Integer height;
    final Integer width;
    final Integer depth;

    private ParcelPackage(Integer weight, Integer height, Integer width, Integer depth) {
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    public static ParcelPackage of(Integer weight, Integer height, Integer width, Integer depth) {
        String type = "";
        if (weight <= 60 && height <= 229 && width <= 162 && depth <= 25) {
            type = "SMALL";
        } else if (weight <= 500 && height <= 324 && width <= 229 && depth <= 100) {
            type = "MEDIUM";
        } else {
            type = "DEFAULT";
        }
        return switch (type) {
            case "SMALL" -> new ParcelPackage(weight, height, width, depth) {
                public Double postageInBaseCurrency() {
                    return 120d;
                }
            };
            case "MEDIUM" -> new ParcelPackage(weight, height, width, depth) {
                public Double postageInBaseCurrency() {
                    return (double) (weight * 4);
                }
            };
            case "DEFAULT" -> new ParcelPackage(weight, height, width, depth) {
                public Double postageInBaseCurrency() {
                    return Math.max(weight, height * width * depth / 1000d) * 6;
                }
            };
            default -> throw new IllegalStateException(STR."Unexpected value: \{type}");
        };
        // How I wish Java had something like this -
        // return switch (type) {
        //     case "SMALL" -> 120d;
        //     case "MEDIUM" -> (double) (weight * 4);
        //     case "DEFAULT" -> Math.max(weight, height * width * depth / 1000d) * 6;
        //     default -> throw new IllegalStateException(STR."Unexpected value: \{type}");
        // };

    }

    public abstract Double postageInBaseCurrency();

    @Override
    public String toString() {
        return STR."Package[weight=\{weight}, height=\{height}, width=\{width}, depth=\{depth}\{']'}";
    }

}
