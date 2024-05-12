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
    public String toString() {
        return STR."Package[weight=\{weight}, height=\{height}, width=\{width}, depth=\{depth}\{']'}";
    }

    private static final class SmallPackage extends ParcelPackage {
        private SmallPackage(Integer weight, Integer height, Integer width, Integer depth) {
            super(weight, height, width, depth);
        }

        public Double postageInBaseCurrency() {
            return 120d;
        }

    }

    private static final class MediumPackage extends ParcelPackage {
        private MediumPackage(Integer weight, Integer height, Integer width, Integer depth) {
            super(weight, height, width, depth);
        }

        public Double postageInBaseCurrency() {
            return (double) (weight * 4);
        }

    }

    private static final class DefaultPackage extends ParcelPackage {
        private DefaultPackage(Integer weight, Integer height, Integer width, Integer depth) {
            super(weight, height, width, depth);
        }

        public Double postageInBaseCurrency() {
            return Math.max(weight, height * width * depth / 1000d) * 6;
        }

    }
}
