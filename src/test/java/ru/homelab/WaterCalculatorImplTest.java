package ru.homelab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.homelab.calculator.WaterCalculator;
import ru.homelab.calculator.WaterCalculatorImpl;
import ru.homelab.exception.WaterCalculatorException;
import ru.homelab.validator.LandscapeValidatorImpl;

public class WaterCalculatorImplTest {

    private WaterCalculator calculator;

    @Before
    public void init() {
        var validator = new LandscapeValidatorImpl();
        calculator = new WaterCalculatorImpl(validator);
    }

    @Test
    public void whenCalculateWaterAmount() {
        //When:
        var landscape = new int[]{5, 1, 2, 4, 5, 0, 3, 1};
        var result = calculator.calculateWaterAmount(landscape);

        //Then:
        Assert.assertEquals(4, result);
    }

    @Test
    public void whenInputArrayIsEmpty() {
        //When:
        var landscape = new int[0];
        var result = calculator.calculateWaterAmount(landscape);

        //Then:
        Assert.assertEquals(0, result);
    }

    @Test(expected = WaterCalculatorException.class)
    public void whenLandscapeSizeIsMoreWeExpect() {
        //When:
        var landscape = new int[1_000_000];
        var result = calculator.calculateWaterAmount(landscape);

        //Then ex:
    }

    @Test(expected = WaterCalculatorException.class)
    public void whenLandscapeContainsOverBigNumbers() {
        //When:
        var landscape = new int[]{5, 3, 4, 1_000_000};
        var result = calculator.calculateWaterAmount(landscape);

        //Then ex:
    }

    @Test
    public void whenNoWaterAfterEvaporate() {
        //When:
        var landscape = new int[]{5, 4, 3, 4, 5, 4, 3, 3, 1};
        var result = calculator.calculateWaterAmount(landscape);

        //Then:
        Assert.assertEquals(0, result);
    }

    @Test
    public void whenBorderHeightsWithDeepLake() {
        //When:
        var landscape = new int[]{5, 0, 0, 0, 0, 0, 0, 0, 5};
        var result = calculator.calculateWaterAmount(landscape);

        //Then:
        Assert.assertEquals(21, result);
    }

    @Test
    public void whenFewHeightsWithPlateaus() {
        //When:
        var landscape = new int[]{5, 1, 1, 5, 5, 4, 0, 0, 0};
        var result = calculator.calculateWaterAmount(landscape);

        //Then:
        Assert.assertEquals(4, result);
    }

    @Test
    public void whenOnlyDeepPit() {
        //When:
        var landscape = new int[]{4, 1, 5};
        var result = calculator.calculateWaterAmount(landscape);

        //Then:
        Assert.assertEquals(1, result);
    }

    @Test
    public void whenOnlySmallPit() {
        //When:
        var landscape = new int[]{2, 1, 3};
        var result = calculator.calculateWaterAmount(landscape);

        //Then:
        Assert.assertEquals(0, result);
    }

    @Test
    public void whenOnlyOneHeight() {
        //When:
        var landscape = new int[]{1, 3, 1};
        var result = calculator.calculateWaterAmount(landscape);

        //Then:
        Assert.assertEquals(0, result);
    }

    @Test
    public void whenMoveDownOnly() {
        //When:
        var landscape = new int[]{3, 2, 1};
        var result = calculator.calculateWaterAmount(landscape);

        //Then:
        Assert.assertEquals(0, result);
    }

    @Test
    public void whenMoveUpOnly() {
        //When:
        var landscape = new int[]{1, 2, 3};
        var result = calculator.calculateWaterAmount(landscape);

        //Then:
        Assert.assertEquals(0, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenExecuteWithNullLandscape() {
        //When:
        calculator.calculateWaterAmount(null);

        //Then ex:
    }

    @Test
    public void whenExecuteWithEmptyLandscape() {
        //When:
        var result = calculator.calculateWaterAmount(new int[]{1, 2});

        //Then:
        Assert.assertEquals(0, result);
    }

    @Test(expected = WaterCalculatorException.class)
    public void whenLandscapeContainsNegativeValues() {
        //When:
        var landscape = new int[]{0, 0, -1};
        var result = calculator.calculateWaterAmount(landscape);

        //Then ex:
    }
}