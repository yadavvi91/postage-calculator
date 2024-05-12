package org.yadavvi;

import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculatorTest {

    private CalculatorApp calculator;

    @BeforeEach
    public void setUp() {
        calculator = new CalculatorApp();
    }

//    1st try
//    public static Object[][] inputs() {
//        return new Object[][]{
//                new Object[]{0, 0, 0, 0, Currency.Eur},
//                new Object[]{0, 0, 0, 0, Currency.Chf}
//        };
//    }

//    // this gives an error "By default, ApprovalTests only allows one verify() call per test."
//    // this means parameterized tests and Approvals test don't mix well.
//    // there's might be a workaround in CombinationApprovals, let's check.
//    // Llewellyn Falco mentions something about this here - https://youtu.be/8OxH9Lz0Ckg?t=1227
//    // I think this is why he uses - Item[] items = new Item[]{new Item(name)};
//    // instead of directly using   - new Item(name);
//    @ParameterizedTest
//    @MethodSource("inputs")
//    public void testBuildString(Integer weight, Integer height, Integer width, Integer depth, Currency currency)
//            throws Exception {
//        Money calculate = calculator.calculate(weight, height, width, depth, currency);
//        Approvals.verify(calculate);
//    }

//    2nd try
//    // this too doesn't compile, but I think I can do and Object[] and it might work
//    @ParameterizedTest
//    @MethodSource("inputs")
//    public void testBuildString(Integer weight, Integer height, Integer width, Integer depth, Currency currency)
//            throws Exception {
//        CombinationApprovals.verifyAllCombinations(this::getCalculate, new Object[]{weight, height, width, depth, currency});
//    }

//    3rd try
//    // this too doesn't work something about passed Object[] instead of IN1[]
//    // let us try with the Stream<Arguments> thing (https://stackoverflow.com/a/69324395) and
//      // (https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-sources-MethodSource)
//    // or the Parameterized Object thing, whichever works (lets try ParamObject first)
//    @ParameterizedTest
//    @MethodSource("inputs")
//    public void testBuildString(Object[] inputs)
//            throws Exception {
//        CombinationApprovals.verifyAllCombinations(this::getCalculate, inputs);
//    }
//
//    private Money getCalculate(Integer weight, Integer height, Integer width, Integer depth, Currency currency)
//            throws Exception {
//        return calculator.calculate(weight, height, width, depth, currency);
//    }

//    4th try
//    // I saw below that just the parameterized object will not work
//    // because it won't satisfy the - Object[][] thing
//    // so let us try a combination of Stream<Arguments> and CombinationApprovals
//    public static Object[][] inputObjects() {
//        return new Object[][]{
//                new Input(0, 0, 0, 0, Currency.Eur),
//                new Object[]{0, 0, 0, 0, Currency.Eur},
//                new Object[]{0, 0, 0, 0, Currency.Chf}
//        };
//    }

//    5th try
//    // this also doesn't work. let's just abandon the parameterized test thing
//    public static Stream<Arguments> inputObjects() {
//        return Stream.of(
//                arguments(new Input(0, 0, 0, 0, Currency.Eur)),
//                arguments(new Input(0, 0, 0, 0, Currency.Chf))
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("inputObjects")
//    public void testBuildString(Input inputs) {
//        CombinationApprovals.verifyAllCombinations(this::getCalculate, inputs);
//    }
//
//    private Money getCalculate(Input input) {
//        Money calculate = null;
//        try {
//            calculate = calculator.calculate(input.weight(), input.height(), input.width(), input.depth(), input.currency());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return calculate;
//    }
//
//    public static record Input(Integer weight, Integer height, Integer width, Integer depth, Currency currency) {
//    }

    private Money getCalculate(Input input) {
        try {
            if (input instanceof Input(var weight, var height, var width, var depth, var currency)) {
                return calculator.calculate(weight, height, width, depth, currency);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public record Input(Integer weight, Integer height, Integer width, Integer depth, Currency currency) {
        public static Input of(Integer weight, Integer height, Integer width, Integer depth, Currency currency) {
            return new Input(weight, height, width, depth, currency);
        }

        public String toString() {
            return STR."Input[\{weight},\{height},\{width},\{depth},\{currency}]";
        }
    }

    public static Input[] zeroDimensionPackagesWithAllCurrencies() {
        return new Input[]{
                Input.of(0, 0, 0, 0, Currency.Gbp),
                Input.of(0, 0, 0, 0, Currency.Eur),
                Input.of(0, 0, 0, 0, Currency.Chf),
                Input.of(0, 0, 0, 0, null)
        };
    }

    @Test
    public void testZeroDimensionPackagesWithAllCurrencies() {
        Input[] inputs = zeroDimensionPackagesWithAllCurrencies();
        CombinationApprovals.verifyAllCombinations(this::getCalculate, inputs);
    }

    // weight <= 60 && height <= 229 && width <= 162 && depth <= 25
    public static Input[] smallPackages() {
        return new Input[]{
                Input.of(60, 229, 162, 25, Currency.Gbp),
                Input.of(61, 229, 162, 25, Currency.Gbp),
                Input.of(60, 230, 162, 25, Currency.Gbp),
                Input.of(60, 229, 163, 25, Currency.Gbp),
                Input.of(60, 229, 162, 26, Currency.Gbp),
        };
    }

    @Test
    public void testSmallPackages() {
        Input[] inputs = smallPackages();
        CombinationApprovals.verifyAllCombinations(this::getCalculate, inputs);
    }

    // weight <= 500 && height <= 324 && width <= 229 && depth <= 100
    public static Input[] mediumPackages() {
        return new Input[]{
                Input.of(500, 324, 229, 100, Currency.Gbp),
                Input.of(501, 324, 229, 100, Currency.Gbp),
                Input.of(500, 325, 229, 100, Currency.Gbp),
                Input.of(500, 324, 230, 100, Currency.Gbp),
                Input.of(500, 324, 229, 101, Currency.Gbp),
        };
    }

    @Test
    public void testMediumPackages() {
        Input[] inputs = mediumPackages();
        CombinationApprovals.verifyAllCombinations(this::getCalculate, inputs);
    }

    // weight <= 500 && height <= 324 && width <= 229 && depth <= 100
    public static Input[] largePackages() {
        return new Input[]{
                Input.of(500, 324, 229, 101, Currency.Gbp),
        };
    }

    @Test
    public void testLargePackages() {
        Input[] inputs = largePackages();
        CombinationApprovals.verifyAllCombinations(this::getCalculate, inputs);
    }

}